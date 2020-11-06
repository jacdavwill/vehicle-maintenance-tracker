package com.example.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

  private static final String url = "jdbc:postgresql:";
//  private static final String url = "jdbc:postgresql://localhost/vehicle-maintenance-tracker";
  private static final String user = "postgres";
//  private static final String user = "tylerlarsen";
  private static final String password = "password";

  public static Connection connect() throws SQLException {
    return connect("vehicle_maintenance");
  }

  public static Connection connect(String databaseName) throws SQLException {
    Connection conn = null;
    try{
      conn = DriverManager.getConnection(url + databaseName, user, password);
      System.out.println("Connected successfully!");
    } catch(SQLException e){
      System.err.println(e.getMessage());
      throw new SQLException("Couldn't connect to database!!");
    }
    return conn;
  }

  public static void main(String[] args) throws SQLException {
    connect();
  }
}
