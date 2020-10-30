package com.example.dataAccess;

import java.io.File;
import java.sql.Statement;
import java.util.Scanner;

public class DummyDataGenerator {
  private static String buildCreateStatementFromFile(String initialString, int numParams, String filename) throws Exception {
    StringBuilder createStatement = new StringBuilder(initialString);
    Scanner scanner = new Scanner(new File(filename));
    while(scanner.hasNextLine()){
      String line = scanner.nextLine();
      String[] tokens = line.split("\\|");
      if(tokens.length != numParams){
        throw new Exception("Invalid number of parameters in file row!!!");
      }
      createStatement.append("(");
      for(int i = 0; i < numParams; i++){
        if(i == numParams - 1){
          createStatement.append("'").append(tokens[i]).append("')");
        }
        else {
          createStatement.append("'").append(tokens[i]).append("', ");
        }
      }
      if(scanner.hasNextLine()) {
        createStatement.append(",\n\t");
      }
    }
    createStatement.append(";");
    return createStatement.toString();
  }

  private static String buildCreateAuthsStatementFromFile(String filename) throws Exception {
    String initialString = "INSERT INTO auth (user_id, auth_token, create_time) VALUES \n\t";
    return buildCreateStatementFromFile(initialString, 3, filename);
  }

  private static String buildCreateUsersStatementFromFile(String filename) throws Exception {
    String initialString = "INSERT INTO users (email, \"password\", salt, display_name, phone) VALUES \n\t";
    return buildCreateStatementFromFile(initialString, 5, filename);
  }

  private static String buildCreateVehiclesStatementFromFile(String filename) throws Exception {
    String initialString = "INSERT INTO vehicle (user_id, nickname, image_url, registration_month, "
        + "mileage, make, model, year, color, transmission_type, energy_type) VALUES \n\t";
    return buildCreateStatementFromFile(initialString, 11, filename);
  }

  private static String buildCreateMaintItemsStatementFromFile(String filename) throws Exception {
    String initialString = "INSERT INTO maint_item (vehicle_id, frequency_months, frequency_miles, "
        + "description, last_completed_date, last_completed_mileage) VALUES \n\t";
    return buildCreateStatementFromFile(initialString, 6, filename);
  }

  private static String buildCreateNotificationsStatementFromFile(String filename) throws Exception {
    String initialString = "INSERT INTO notification (maint_item_id, past_due, status) VALUES \n\t";
    return buildCreateStatementFromFile(initialString, 3, filename);
  }

  private static String buildCreateMaintEventsStatementFromFile(String filename) throws Exception {
    String initialString = "INSERT INTO maint_event (maint_item_id, event_date, mileage, "
        + "location, company, description) VALUES \n\t";
    return buildCreateStatementFromFile(initialString, 6, filename);
  }
  public static void main(String[] args){
    try {
//      DbTableManager.refreshDB();
      String createUsers = buildCreateUsersStatementFromFile("src/main/resources/dummyUsers.txt");
      String createVehicles = buildCreateVehiclesStatementFromFile("src/main/resources/dummyVehicles.txt");
      String createAuths = buildCreateAuthsStatementFromFile("src/main/resources/dummyAuths.txt");
      String createMaintItems = buildCreateMaintItemsStatementFromFile("src/main/resources/dummyMaintItems.txt");
      String createMaintEvents = buildCreateMaintEventsStatementFromFile("src/main/resources/dummyMaintEvents.txt");
      String createNotifications = buildCreateNotificationsStatementFromFile("src/main/resources/dummyNotifications.txt");

      Statement stmt = DbConnection.connect().createStatement();
      stmt.executeUpdate(createUsers);
      stmt.executeUpdate(createVehicles);
      stmt.executeUpdate(createAuths);
      stmt.executeUpdate(createMaintItems);
      stmt.executeUpdate(createMaintEvents);
      stmt.executeUpdate(createNotifications);
    } catch (Exception e){
      System.out.println("Problem! " + e.getMessage());
    }
  }
}
