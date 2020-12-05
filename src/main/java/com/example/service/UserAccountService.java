package com.example.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

import com.example.dataAccess.AuthDao;
import com.example.dataAccess.UserDao;
import com.example.exceptions.AlreadyExistsException;
import com.example.exceptions.InternalServiceException;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Auth;
import com.example.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO: Finish Services
@Service
public class UserAccountService extends com.example.service.Service {

  @Autowired
  private EmailService emailService;
  @Autowired
  private UserDao userDao;
  @Autowired
  private AuthDao authDao;

  /**
   * Registers a user in the database if the username is not taken.
   * 
   * @param email
   * @param password
   * @param displayName
   * @param phoneNumber
   * @return Auth Token if successful, Error if failed (username taken)
   * @throws InternalServiceException
   * @throws UnauthorizedException
   * @throws AlreadyExistsException
   */
  public String register(String email, String password, String displayName, String phoneNumber)
      throws InternalServiceException, UnauthorizedException, AlreadyExistsException {

    User user = userDao.retrieveUser(email);
    if (user != null) {
      throw new AlreadyExistsException("Email already in use");
    }
        
    String salt = this.getSalt();
    String hashedPassword = this.getHashedPassword(salt, password);

    user = new User(email, hashedPassword, salt, displayName, phoneNumber);
    userDao.createUser(user);
    return this.login(email, password);
  }

  private String getSalt() {
    SecureRandom random = new SecureRandom();

    String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lower = upper.toLowerCase();
    String digits = "0123456789";
    String special = "~!@#$%^&*()_-+=?/>.<,|}]{[";
    String specialAlphaNumeric = upper + lower + digits + special;

    StringBuilder salt = new StringBuilder();
    int saltLen = 20;
    for (int i = 0; i < saltLen; i++) {
      int index = random.nextInt() % specialAlphaNumeric.length();
      if (index < 0) {
        index += specialAlphaNumeric.length();
      }
      char c = specialAlphaNumeric.charAt(index);
      salt.append(c);
    }

    return salt.toString();
  }

  private String getHashedPassword(String salt, String password) throws InternalServiceException {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new InternalServiceException();
    }
    md.update((password + salt).getBytes(StandardCharsets.UTF_8));
    byte[] hash = md.digest();
    return DatatypeConverter.printHexBinary(hash);
  }

  /**
   * Checks that the user exists in the database (and matches)
   * 
   * @param email
   * @param password
   * @return Auth Token if successful, Error if failed (incorrect data)
   * @throws UnauthorizedException
   * @throws InternalServiceException
   */
  public String login(String email, String password) throws InternalServiceException, UnauthorizedException {
    User user = userDao.retrieveUser(email);

    if (user == null || !this.getHashedPassword(user.getSalt(), password).equals(user.getPassword())) {
      throw new UnauthorizedException("Incorrect username or password");
    }

    Auth auth = authDao.retrieveAuth(user.getUserId());
    if (auth != null) {
      authDao.deleteAuth(user.getUserId());
    }

    auth = new Auth(user.getUserId(), this.getSalt());
    authDao.createAuth(auth);
    return auth.getAuthToken();
  }

  /**
   * Deletes/Invalidates authToken from database to prevent future calls
   * @param authToken
   * @return Success if token was invalidated, error otherwise
   */
  public String deleteAuthToken(String authToken) {
    authDao.deleteAuth(authToken);
    return "success";
  }

  /**
   * Sends unique code to email to reset password
   * @param email
   * @return
   */
  public void requestPasswordReset(String email) throws NotFoundException, InternalServiceException {
    User user = userDao.retrieveUser(email);
    if (user == null) {
      throw new NotFoundException("Email is not registered");
    }
    Auth resetToken = new Auth(user.getUserId(), this.getSalt());
    String resetLink = "http://dsj82bv0v2l47.cloudfront.net/reset-password?resetToken=" + URLEncoder.encode(resetToken.getAuthToken(), StandardCharsets.UTF_8);
    authDao.deleteAuth(user.getUserId());
    authDao.createAuth(resetToken);
    emailService.sendEmail(email, "Password Reset Request", "Go to the following link in order to reset your password: " + resetLink);
  }

  /**
   * Updates account information in database if user has valid resetToken or
   * AuthToken if AuthToken then updates all info. if ResetToken then just updates
   * password.
   * 
   * @param resetToken
   * @param authToken
   * @param email
   * @param password
   * @param displayName
   * @param phoneNumber
   * @return Success, or error message on failure (doesn't exist or invalid token)
   * @throws UnauthorizedException
   * @throws InternalServiceException
   */
  public String updateUser(String resetToken, String authToken, String email, String password, String displayName,
      String phoneNumber) throws UnauthorizedException, InternalServiceException {
                                
    boolean authTokenUsed = false;
    Integer userId;

    if (resetToken != null && !resetToken.equals("")) {
      userId = this.getUserIdFromAuthToken(resetToken);
    } else {
      userId = this.getUserIdFromAuthToken(authToken);
      authTokenUsed = true;
    }
    
    User user = userDao.retrieveUser(userId);

    String salt = this.getSalt();
    password = this.getHashedPassword(salt, password);
    user.updatePassword(salt, password);

    if (authTokenUsed) {
      if (email != null && !email.equals("")) {
        user.setEmail(email);
      }
      if (displayName != null && !displayName.equals("")) {
        user.setDisplayName(displayName);
      }
      if (phoneNumber != null && !phoneNumber.equals("")) {
        user.setPhone(phoneNumber);
      }
    }

    userDao.updateUser(user);
    return "success";
  }
}
