package com.example.dataAccess;

import com.example.model.Notification;
import java.util.List;

public interface INotificationDao {
  Notification retrieveNotification(int notificationId);
  void createNotification(Notification newNotification);
  void deleteNotification(int notificationId);
  void updateNotification(Notification updatedNotification);

  List<Notification> retrieveNotifications(int vehicleId);
}
