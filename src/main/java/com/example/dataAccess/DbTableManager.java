package com.example.dataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DbTableManager {
  private static final String drop_tables = "DROP TABLE IF EXISTS auth, users, vehicle, maint_item, maint_event, notification CASCADE;";
  private static final String create_database = "CREATE DATABASE vehicle_maintenance;";

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
    stmt.executeUpdate(DbGenerator.create_user);
    stmt.executeUpdate(DbGenerator.create_auth);
    stmt.executeUpdate(DbGenerator.create_vehicle);
    stmt.executeUpdate(DbGenerator.create_maint_item);
    stmt.executeUpdate(DbGenerator.create_maint_event);
    stmt.executeUpdate(DbGenerator.create_notification);
    stmt.close();
    conn.close();
    log.info("Updated tables!!");
  }
}
