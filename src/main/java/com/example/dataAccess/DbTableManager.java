package com.example.dataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DbTableManager {
  private static final String drop_tables = "DROP TABLE IF EXISTS auth, users, vehicle, maint_item, maint_event, notification CASCADE;";
  private static final String create_database = "CREATE DATABASE vehicle_maintenance;";

  private static final String create_auth = "CREATE TABLE IF NOT EXISTS auth (" +
      "user_id INT PRIMARY KEY REFERENCES users(user_id), " +
      "auth_token VARCHAR(25), " +
      "create_time DATE" +
      ");";

  private static final String create_user = "CREATE TABLE IF NOT EXISTS users (" +
      "user_id SERIAL PRIMARY KEY, " +
      "email VARCHAR(50) UNIQUE, " +
      "password VARCHAR(50), " +
      "salt VARCHAR(25), " +
      "display_name VARCHAR(25), " +
      "phone VARCHAR(15)" +
      ");";

  private static final String create_vehicle = "CREATE TABLE IF NOT EXISTS vehicle (" +
      "vehicle_id SERIAL PRIMARY KEY, " +
      "user_id INT REFERENCES users(user_id), " +
      "nickname VARCHAR(25), " +
      "image_url VARCHAR(50), " +
      "registration_month VARCHAR(15), " +
      "mileage INT, " +
      "make VARCHAR(20), " +
      "model VARCHAR(20), " +
      "year INT, " +
      "color VARCHAR(15), " +
      "transmission_type VARCHAR(15), " +
      "energy_type VARCHAR(15)" +
      ");";

  private static final String create_maint_item = "CREATE TABLE IF NOT EXISTS maint_item (" +
      "maint_item_id SERIAL PRIMARY KEY, " +
      "vehicle_id INT REFERENCES vehicle(vehicle_id), " +
      "frequency_months INT, " +
      "frequency_miles INT, " +
      "description VARCHAR(100), " +
      "last_completed_date DATE, " +
      "last_completed_mileage INT" +
      ");";

  private static final String create_maint_event = "CREATE TABLE IF NOT EXISTS maint_event (" +
      "maint_event_id SERIAL PRIMARY KEY, " +
      "maint_item_id INT REFERENCES maint_item(maint_item_id), " +
      "event_date DATE, " +
      "mileage INT, " +
      "location VARCHAR(50), " +
      "company VARCHAR(50), " +
      "description VARCHAR(100)" +
      ");";

  private static final String create_notification = "CREATE TABLE IF NOT EXISTS notification (" +
      "notification_id SERIAL PRIMARY KEY, " +
      "maint_item_id INT REFERENCES maint_item(maint_item_id), " +
      "past_due BOOLEAN, " +
      "status VARCHAR(20)" +
      ");";

  public static void main(String[] args){
    try {
      refreshDB();
    } catch (SQLException e){
      log.error("It's not working:(", e);
    }
  }

  static void createDB() throws SQLException {
    Connection conn = DbConnection.connect("postgres");
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(create_database);
    stmt.close();
    conn.close();
  }

  static void refreshDB() throws SQLException {
    dropTables();
    createTables();
  }

  private static void dropTables() throws SQLException {
    Connection conn = DbConnection.connect();
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(drop_tables);
    stmt.close();
    conn.close();
    log.info("Dropped tables!!");
  }

  private static void createTables() throws SQLException {
    Connection conn = DbConnection.connect();
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(create_user);
    stmt.executeUpdate(create_auth);
    stmt.executeUpdate(create_vehicle);
    stmt.executeUpdate(create_maint_item);
    stmt.executeUpdate(create_maint_event);
    stmt.executeUpdate(create_notification);
    stmt.close();
    conn.close();
    log.info("Updated tables!!");
  }
}
