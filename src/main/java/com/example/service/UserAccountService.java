package com.example.service;

//TODO: Finish Services
public class UserAccountService {

  /**
   * Registers a user in the database if the username is not taken.
   * @param email
   * @param password
   * @param displayName
   * @param phoneNumber
   * @return Auth Token if successful, Error if failed (username taken)
   */
  public String register(String email, String password, String displayName, String phoneNumber) {
    return "";
  }

  /**
   * Checks that the user exists in the database (and matches)
   * @param email
   * @param password
   * @return Auth Token if successful, Error if failed (incorrect data)
   */
  public String login(String email, String password) {
    return "";
  }

  /**
   * Deletes/Invalidates authToken from database to prevent future calls
   * @param authToken
   * @return Success if token was invalidated, error otherwise
   */
  public String deleteAuthToken(String authToken) {
    return "";
  }

  /**
   * Sends unique code to email to reset password
   * @param email
   * @return
   */
  public String requestPasswordReset(String email) {
    return "";
  }

  /**
   * Updates account information in database if user has valid resetToken or AuthToken
   * if AuthToken then updates all info.
   * if ResetToken then just updates password.
   * @param resetToken
   * @param authToken
   * @param email
   * @param password
   * @param displayName
   * @param phoneNumber
   * @return Success, or error message on failure (doesn't exist or invalid token)
   */
  public String resetPassword(String resetToken, String authToken, String email,
                              String password, String displayName, String phoneNumber) {
    return "";
  }
}
