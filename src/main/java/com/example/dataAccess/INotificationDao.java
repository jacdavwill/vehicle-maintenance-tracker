package com.example.dataAccess;

import com.example.model.Notification;
import java.sql.SQLException;
import java.util.List;

public interface INotificationDao {
  Notification retrieveNotification(int notificationId) throws SQLException;
  void createNotification(Notification newNotification) throws SQLException;
  void deleteNotification(int notificationId) throws SQLException;
  void updateNotification(Notification updatedNotification) throws SQLException;

  List<Notification> retrieveNotifications(int vehicleId) throws SQLException;
}
