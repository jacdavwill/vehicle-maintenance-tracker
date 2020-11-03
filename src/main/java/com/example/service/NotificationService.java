package com.example.service;

import com.example.dataAccess.INotificationDao;
import com.example.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  @Autowired
  INotificationDao notificationDao;

  public Notification getNotification(String authToken, Integer notificationId){
    return notificationDao.retrieveNotification(notificationId);
  }
}
