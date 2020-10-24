package com.example.dataAccess;

import com.example.model.Notification;
import java.sql.SQLException;
import java.util.List;

public class NotificationDao implements INotificationDao {

  private final String SELECT_NOTIFICATION = "SELECT * FROM notification WHERE notification_id = ?";
  private final String SELECT_NOTIFICATIONS = "SELECT * FROM notification WHERE vehicle_id = ?";


  @Override
  public Notification retrieveNotification(int notificationId) throws SQLException {

    return null;
  }

  @Override
  public void createNotification(Notification newNotification) {

  }

  @Override
  public void deleteNotification(int notificationId) {

  }

  @Override
  public void updateNotification(Notification updatedNotification) {

  }

  @Override
  public List<Notification> retrieveNotifications(int vehicleId) {
    return null;
  }
}
