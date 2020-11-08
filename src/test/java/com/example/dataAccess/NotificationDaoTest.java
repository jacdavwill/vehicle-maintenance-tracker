package com.example.dataAccess;

import com.example.model.Notification;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

// good resource: https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html

@Log4j2
@SpringBootTest
@ContextConfiguration(classes=VehicleMaintenanceTrackerApplication.class)
class NotificationDaoTest {

  @Autowired
  NotificationDao notificationDao;

  @Test
  void retrieveNotification() {
    Notification notification = notificationDao.retrieveNotification(1);
    log.info("Notification status: " + notification.getStatus());
  }

  @Test
  void createNotification() {
    //TODO: need to make sure this maint item exists in db
    Notification notification = new Notification(null, 1, true, "Waaay late");
    int notificationId = notificationDao.createNotification(notification);
    log.info("Notification id: " +  notificationId);
  }

  @Test
  void deleteNotification() {
    notificationDao.deleteNotification(4);
  }

  @Test
  void updateNotification() {
    Notification updatedNotification = new Notification(1, 1, true, "never done");
    notificationDao.updateNotification(updatedNotification);
  }

  @Test
  void retrieveNotifications() {
    List<Notification> notifications = notificationDao.retrieveNotifications(1);
    log.info("Number of notifications: " + notifications.size());
  }
}