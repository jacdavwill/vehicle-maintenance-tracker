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

//TODO: Finish Services
public class UserAccountService extends Service {

  /**
   * Registers a user in the database if the username is not taken.
   * 
   * @param email
   * @param password
   * @param displayName
   * @param phoneNumber
   * @return sessionKey if successful, Error if failed (username taken)
   */
  public String register(String email, String password, String displayName, String phoneNumber) throws Exception {
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
   * @param email
   * @param password
   * @return sessionKey if successful, Error if failed (incorrect data)
   */
  public String login(String email, String password) throws Exception {
    IUserDao userDao = new UserDao();
    User user = userDao.retrieveUserFromEmail(email);

    if (user == null || !this.getHashedPassword(user.getSalt(), password).equals(user.getPassword())) {
      throw new UnauthorizedException("Incorrect username or password");
    }

    Auth auth = new Auth(user.getUserId());
    IAuthDao authDao = new AuthDao();
    authDao.createAuth(auth);
    return auth.getSessionKey();
  }

  /**
   * Deletes/Invalidates sessionKey from database to prevent future calls
   * @param sessionKey
   * @return Success if token was invalidated, error otherwise
   */
  public String deleteSessionKey(String sessionKey) {
    IAuthDao authDao = new AuthDao();
    authDao.deleteAuth(sessionKey);
    return "success";
  }

  /**
   * Sends unique code to email to reset password
   * @param email
   * @return
   */
  public String requestPasswordReset(String email) { //TODO
    return "";
  }

  /**
   * Updates account information in database if user has valid resetToken or SessionKey
   * if SessionKey then updates all info.
   * if ResetToken then just updates password.
   * @param resetToken
   * @param sessionKey
   * @param email
   * @param password
   * @param displayName
   * @param phoneNumber
   * @return Success, or error message on failure (doesn't exist or invalid token)
   */
  public String updateUser(String resetToken, String sessionKey, String email,
                              String password, String displayName, String phoneNumber)
                                throws UnauthorizedException, InternalServiceException {
                                
    boolean sessionKeyUsed = false;
    Integer userId;

    if (resetToken != null && !resetToken.equals("")) {
      this.checkValidSessionKey(resetToken);
      userId = this.getUserFromSessionKey(resetToken);
    } else {
      this.checkValidSessionKey(sessionKey);
      userId = this.getUserFromSessionKey(sessionKey);
      sessionKeyUsed = true;
    }
    
    IUserDao userDao = new UserDao();
    User user = userDao.retrieveUser(userId);

    String salt = this.getSalt();
    password = this.getHashedPassword(salt, password);
    user.updatePassword(salt, password);

    if (sessionKeyUsed) {
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
