package com.example.service;

import com.example.dataAccess.INotificationDao;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Notification;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@Log4j2
@Service
public class NotificationService extends com.example.service.Service {

  @Autowired
  INotificationDao notificationDao;

  public Notification getNotification(String authToken, Integer notificationId) throws NotFoundException, UnauthorizedException {
    this.checkValidAuthToken(authToken);
    int userId = this.getUserFromAuthToken(authToken);
    Notification notification = notificationDao.retrieveNotification(notificationId);
    if(notification == null) {
      throw new NotFoundException("Notification not found");
    }
//    if (notification.getUserId() != userId) {
//      throw new UnauthorizedException();
//    }
    return notification;
  }
}
