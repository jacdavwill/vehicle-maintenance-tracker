package com.example.service;

import com.example.dataAccess.IMaintItemDao;
import com.example.dataAccess.INotificationDao;
import com.example.dataAccess.IUserDao;
import com.example.dataAccess.IVehicleDao;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.MaintItem;
import com.example.model.Notification;
import com.example.model.User;
import com.example.model.Vehicle;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class NotificationService extends com.example.service.Service {

  @Autowired
  INotificationDao notificationDao;
  @Autowired
  IMaintItemDao maintItemDao;
  @Autowired
  IVehicleDao vehicleDao;
  @Autowired
  IUserDao userDao;
  @Autowired
  EmailService emailService;

  /**
   * Executes once every minute
   * Sends an email to each registered user with a list of previous and upcoming maintenance dates for each
   * maintenance event type.
   * */
  @Scheduled(fixedRate = 60000) // runs once a minute
  private void sendMaintenanceRemindersTask() {
    System.out.println("Checking for notifications to send!!");
    List<MaintItem> maintItemsDue = maintItemDao.retrieveMaintItemsDueForNotification();
    for(MaintItem maintItem : maintItemsDue){
      Notification notification = notificationDao.retrieveNotificationForMaintItemId(maintItem.getMaintItemId());
      if(notification == null){ // if there is not yet a notification for this due maint item
        notificationDao.createNotification(new Notification(0, maintItem.getMaintItemId(), "Maint Item Due: " + maintItem.getDescription()));
        sendEmailNotification(maintItem);
      }
    }
  }

  private void sendEmailNotification(MaintItem maintItem) {
    try {
      Vehicle vehicle = vehicleDao.retrieveVehicle(maintItem.getVehicleId());
      User user = userDao.retrieveUser(vehicle.getUserId());
      log.info("Should be sending email to {}", user.email);
      emailService.sendEmail(user.email, "Vehicle due for maintenance!", createEmailBody(maintItem, vehicle));
    } catch (Exception e) {
      log.error("Error sending email notification for maint item id {}", maintItem.getMaintItemId());
    }
  }

  private String createEmailBody(MaintItem maintItem, Vehicle vehicle) {
    StringBuilder emailBody = new StringBuilder();
    emailBody.append("Your vehicle ");
    if(vehicle.getNickname() != null){
      emailBody.append(vehicle.getNickname()).append(" ");
    }
    emailBody.append("has the following maintenance item due: \n\n");
    if(maintItem.getDescription() != null) {
      emailBody.append(maintItem.getDescription());
    }
    return emailBody.toString();
  }

  /**
   * Returns the notification associated with the notification id. Checks to make sure that the
   * user associated with the authToken is also the user found through notification -> maintItem ->
   * vehicle -> user.
   * @param authToken The authToken provided from the user
   * @param notificationId The desired notification id
   * @return The Notification object of the notification id
   * @throws NotFoundException Thrown if the notification, maintItem, or Vehicle are not found
   * @throws UnauthorizedException Thrown if the user does not have access to view the notification
   */
  public Notification getNotification(String authToken, Integer notificationId) throws NotFoundException, UnauthorizedException {
    int userId = this.getUserIdFromAuthToken(authToken);
    Notification notification = notificationDao.retrieveNotification(notificationId);
    if(notification == null) {
      throw new NotFoundException("Notification not found");
    }
    MaintItem maintItem = maintItemDao.retrieveMaintItem(notification.getMaintItemId());
    if(maintItem == null) {
      throw new NotFoundException("MaintItem not found");
    }
    checkValidVehicle(userId, maintItem.getVehicleId());
    return notification;
  }

  public List<Notification> getAllNotifications(String authToken) throws UnauthorizedException {
    int userId = this.getUserIdFromAuthToken(authToken);
    return notificationDao.retrieveNotifications(userId);
  }

  public Integer addNotification(String authToken, Notification notification) throws UnauthorizedException, NotFoundException {
    int userId = this.getUserIdFromAuthToken(authToken);
    MaintItem maintItem = maintItemDao.retrieveMaintItem(notification.getMaintItemId());
    if(maintItem == null) {
      throw new NotFoundException("MaintItem not found");
    }
    checkValidVehicle(userId, maintItem.getVehicleId());
    return notificationDao.createNotification(notification);
  }

  public void deleteNotification(String authToken, Integer notificationId) throws UnauthorizedException, NotFoundException {
    this.getNotification(authToken, notificationId);
    notificationDao.deleteNotification(notificationId);
  }
}
