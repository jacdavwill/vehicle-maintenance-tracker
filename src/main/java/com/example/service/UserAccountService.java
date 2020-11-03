package com.example.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.example.dataAccess.AuthDao;
import com.example.dataAccess.IAuthDao;
import com.example.dataAccess.IUserDao;
import com.example.dataAccess.UserDao;
import com.example.exceptions.InternalServiceException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Auth;
import com.example.model.User;

import org.springframework.stereotype.Service;

//TODO: Finish Services
@Service
public class UserAccountService extends com.example.service.Service {

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
   */
  public String register(String email, String password, String displayName, String phoneNumber)
      throws InternalServiceException, UnauthorizedException {
        
    String salt = this.getSalt();
    password = this.getHashedPassword(salt, password);

    User user = new User(email, password, salt, displayName, phoneNumber);

    IUserDao userDao = new UserDao();
    userDao.createUser(user);
    return this.login(email, password);
  }

  private String getSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return new String(salt, StandardCharsets.UTF_8);
  }

  private String getHashedPassword(String salt, String password) throws InternalServiceException {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA-512");
    } catch (NoSuchAlgorithmException e) {
      throw new InternalServiceException();
    }
    md.update(salt.getBytes(StandardCharsets.UTF_8));
    byte[] hash = md.digest((password + salt).getBytes(StandardCharsets.UTF_8));
    return new String(hash, StandardCharsets.UTF_8);
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
    IUserDao userDao = new UserDao();
    User user = userDao.retrieveUser(email);

    if (user == null || !this.getHashedPassword(user.getSalt(), password).equals(user.getPassword())) {
      throw new UnauthorizedException("Incorrect username or password");
    }

    Auth auth = new Auth(user.getUserId());
    IAuthDao authDao = new AuthDao();
    authDao.createAuth(auth);
    return auth.getAuthToken();
  }

  /**
   * Deletes/Invalidates authToken from database to prevent future calls
   * @param authToken
   * @return Success if token was invalidated, error otherwise
   */
  public String deleteAuthToken(String authToken) {
    IAuthDao authDao = new AuthDao();
    authDao.deleteAuth(authToken);
    return "success";
  }

  /**
   * Sends unique code to email to reset password
   * @param email
   * @return
   */
  public String requestPasswordReset(String email) {
    // TODO
    return "";
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
      this.checkValidAuthToken(resetToken);
      userId = this.getUserFromAuthToken(resetToken);
    } else {
      this.checkValidAuthToken(authToken);
      userId = this.getUserFromAuthToken(authToken);
      authTokenUsed = true;
    }
    
    IUserDao userDao = new UserDao();
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
