package com.example.dataAccess;

import com.example.model.Notification;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Log4j2
@SpringBootTest
@ContextConfiguration(classes=VehicleMaintenanceTrackerApplication.class)
@ActiveProfiles(profiles = "test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class NotificationDaoTest {

  @Autowired
  DbGenerator dbGenerator;
  @Autowired
  NotificationDao notificationDao;

  @BeforeAll
  void setUp(){
    dbGenerator.dropTables();
    dbGenerator.createTables();
    try {
      dbGenerator.createDummyData("src/main/resources/dummyData.txt");
    } catch(Exception e){
      log.error("Problem with the Dummy Data file!", e);
    }
  }

  @Test
  void retrieveNotification() {
    Notification notification = notificationDao.retrieveNotification(1);
    Notification expected = new Notification(1, 1, false, "Incomplete");
    assertThat(notification).isEqualTo(expected);
  }

  @Test
  void retrieveNotification_DoesntExist() {
    assertThat(notificationDao.retrieveNotification(20)).isNull();
  }

  @Test
  void createNotification() {
    Notification notification = new Notification(0, 1, true, "Waaay late");
    int notificationId = notificationDao.createNotification(notification);
    assertThat(notificationId).isEqualTo(5);
    Notification result = notificationDao.retrieveNotification(5);
    Notification expected = new Notification(5, 1, true, "Waaay late");
    assertThat(result).isEqualTo(expected);
  }

  // TODO: maybe we should make some other data access exception that we throw
  @Test
  void createNotification_InvalidMaintItemId() {
    Notification notification = new Notification(0, 20, true, "Incomplete");
    assertThatThrownBy(()->notificationDao.createNotification(notification)).isInstanceOf(
        DataAccessException.class);
  }

  @Test
  void deleteNotification() {
    Notification notification = new Notification(0, 1, false, "Completed");
    int notificationId = notificationDao.createNotification(notification);
    notificationDao.deleteNotification(notificationId);
    assertThat(notificationDao.retrieveNotification(notificationId)).isNull();
  }

  @Test
  void deleteNotification_InvalidNotificationId_ExceptionNotThrown() {
    notificationDao.deleteNotification(20);
  }

  @Test
  void updateNotification() {
    Notification updatedNotification = new Notification(1, 2, true, "never done");
    notificationDao.updateNotification(updatedNotification);
    Notification result = notificationDao.retrieveNotification(1);
    assertThat(result).isEqualTo(updatedNotification);
  }

  @Test
  void updateNotification_InvalidNotificationId_JustDoesntDoAnything() {
    Notification updatedNotification = new Notification(20, 2, true, "never done");
    notificationDao.updateNotification(updatedNotification); // this does not throw an error - it just won't modify the db at all
    assertThat(notificationDao.retrieveNotification(20)).isNull();
  }

  @Test
  void retrieveNotifications() {
    Notification not1 = new Notification(1, 1, false, "Incomplete");
    Notification not2 = new Notification(3, 3, true, "Complete");
    Notification not3 = new Notification(4, 1, true, "Incomplete");

    List<Notification> notifications = notificationDao.retrieveNotifications(1);
    assertThat(notifications).isEqualTo(Arrays.asList(not1, not2, not3));
  }
}