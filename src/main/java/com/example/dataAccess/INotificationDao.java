package com.example.dataAccess;

import com.example.model.Notification;
import java.sql.SQLException;
import java.util.List;

public interface INotificationDao {
  Notification retrieveNotification(Integer notificationId);
  Integer createNotification(Notification newNotification);
  void deleteNotification(Integer notificationId);
  void updateNotification(Notification updatedNotification);

  List<Notification> retrieveNotifications(Integer vehicleId);
}
