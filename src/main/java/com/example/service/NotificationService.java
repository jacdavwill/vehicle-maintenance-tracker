package com.example.service;

import com.example.dataAccess.INotificationDao;
import com.example.model.Notification;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class NotificationService {

  @Autowired
  INotificationDao notificationDao;

  public Notification getNotification(String authToken, Integer notificationId){
    try {
      return notificationDao.retrieveNotification(notificationId);
    } catch(Exception e){
      log.error("Problem with getNotification call!", e);
      throw e;
    }
  }
}
