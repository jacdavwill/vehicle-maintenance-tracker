package com.example.dataAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class DbGenerator {

  @Autowired
  JdbcTemplate jdbc;

  @Autowired
  NamedParameterJdbcTemplate namedJdbc;

  private static final String drop_tables = "DROP TABLE IF EXISTS auth, users, vehicle, maint_item, maint_event, notification CASCADE;";

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
    DbGenerator dbGenerator = new DbGenerator();
    try {
      dbGenerator.createDummyData("src/main/resources/dummyData.txt");
    }catch(Exception e){
      log.error("Problem!!", e);
    }
  }

  public void createTables() {
    jdbc.execute(create_user);
    jdbc.execute(create_auth);
    jdbc.execute(create_vehicle);
    jdbc.execute(create_maint_item);
    jdbc.execute(create_maint_event);
    jdbc.execute(create_notification);
    log.info("Created tables!!");
  }

  public void dropTables() {
    jdbc.execute(drop_tables);
    log.info("Dropped tables!!");
  }

  public void createDummyData(String dummyDataFile) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(dummyDataFile));
    while(scanner.hasNextLine()){
      String tableInfo = scanner.nextLine();
      String initialSql = generateInitialSql(tableInfo);
      boolean sectionFinished = false;
      while(!sectionFinished){
        if(!scanner.hasNextLine()){
          break;
        }
        String dataLine = scanner.nextLine();
        if(dataLine.isEmpty()){
          sectionFinished = true;
        }else{
          String sql = generateSqlString(initialSql, dataLine);
          log.info("SQL to be executed: {}", sql);
          jdbc.update(sql);
        }
      }
    }
  }

  private String generateInitialSql(String tableInfo){
    String[] tableInfoParts = tableInfo.split("\\(");
    String tableName = tableInfoParts[0];
    String fieldNameList = tableInfoParts[1].replace(")", "");
    String[] fieldNames = fieldNameList.split("\\|");
    StringBuilder sqlBuilder = new StringBuilder();
    sqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
    for(int i = 0; i < fieldNames.length; i++){
      sqlBuilder.append(fieldNames[i]);
      if(i != fieldNames.length-1){
        sqlBuilder.append(",");
      }else{
        sqlBuilder.append(")");
      }
    }
    return sqlBuilder.toString();
  }

  private String generateSqlString(String initialSql, String dataLine){
    StringBuilder sqlBuilder = new StringBuilder(initialSql);
    sqlBuilder.append(" VALUES (");
    String[] fieldValues = dataLine.split("\\|");
    for(int i = 0; i < fieldValues.length; i++){
      sqlBuilder.append("'").append(fieldValues[i]).append("'");
      if(i != fieldValues.length-1){
        sqlBuilder.append(",");
      }else{
        sqlBuilder.append(")");
      }
    }
    return sqlBuilder.toString();
  }
}
