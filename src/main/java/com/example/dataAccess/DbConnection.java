package com.example.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DbConnection {

  private static final String url = "jdbc:postgresql:vehicle-maintenance-tracker";
//  private static final String url = "jdbc:postgresql://localhost/vehicle-maintenance-tracker";
  private static final String user = "postgres";
//  private static final String user = "tylerlarsen";
  private static final String password = "jmcBudget452";

  public static Connection connect() throws SQLException {
    return connect("vehicle_maintenance");
  }

  public static Connection connect(String databaseName) throws SQLException {
    Connection conn = null;
    try{
      conn = DriverManager.getConnection(url + databaseName, user, password);
      log.info("Connected successfully!");
    } catch(SQLException e){
      log.error("Something went wrong!", e);
      throw new SQLException("Couldn't connect to database!!");
    }
    return conn;
  }

  public static void main(String[] args) throws SQLException {
    connect();
  }
}
