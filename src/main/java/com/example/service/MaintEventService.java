package com.example.service;

import com.example.dataAccess.MaintEventDao;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.MaintEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

//TODO: Finish services
@Service
public class MaintenanceEventService extends com.example.service.Service {

  @Autowired
  MaintEventDao maintEventDao;

  public List<MaintEvent> getAllEvents(String authToken, Integer vehicleID)
          throws UnauthorizedException {
    this.checkValidAuthToken(authToken);
    return maintEventDao.retrieveMaintEvents(vehicleID);
  }

  // Might add check to see if vehicle is associated with user.
  public MaintEvent getEvent(String authToken, Integer vehicleID, Integer eventID)
          throws UnauthorizedException, NotFoundException {
    this.checkValidAuthToken(authToken);
    MaintEvent maintEvent = maintEventDao.retrieveMaintEvent(eventID);
    if (maintEvent == null) {
      throw new NotFoundException("MaintEvent not found");
    }
    return maintEvent;
  }

  // Needs maintItemID
  public Integer addEvent(String authToken, Integer vehicleID, MaintEvent newEvent)
          throws UnauthorizedException {
    this.checkValidAuthToken(authToken);
    return maintEventDao.createMaintEvent(newEvent);
  }

  public void updateEvent(String authToken, Integer vehicleID, Integer eventID, MaintEvent updatedEvent)
          throws UnauthorizedException, NotFoundException {
    MaintEvent oldEvent = this.getEvent(authToken, vehicleID, eventID);

    // Not sure if we want to allow dates to be edited or rather set by database
    LocalDate date = updatedEvent.getEventDate();
    Integer mileage = updatedEvent.getMileage();
    String location = updatedEvent.getLocation();
    String description = updatedEvent.getDescription();
    String company = updatedEvent.getCompany();
    if (date != null) {
      oldEvent.setEventDate(date);
    }
    if (mileage != null) {
      oldEvent.setMileage(mileage);
    }
    if (this.isFieldSet(location)) {
      oldEvent.setLocation(location);
    }
    if (this.isFieldSet(description)) {
      oldEvent.setDescription(description);
    }
    if (this.isFieldSet(company)) {
      oldEvent.setCompany(company);
    }

    maintEventDao.updateMaintEvent(oldEvent);

  }

  public void deleteEvent(String authToken, Integer vehicleID, Integer eventID)
          throws UnauthorizedException, NotFoundException{
    MaintEvent maintEvent = this.getEvent(authToken, vehicleID, eventID);
    maintEventDao.deleteMaintEvent(eventID);
  }


}
