package com.example.service;

import com.example.dataAccess.MaintEventDao;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.MaintEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

//TODO: Finish services
@Service
public class MaintEventService extends com.example.service.Service {

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
    MaintEvent maintEvent=maintEventDao.retrieveMaintEvent(eventID);
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
    MaintEvent oldEvent=this.getEvent(authToken, vehicleID, eventID);
    updatedEvent.setMaintEventId(eventID);
    if (Objects.isNull(updatedEvent.getEventDate())) {
      updatedEvent.setEventDate(oldEvent.getEventDate());
    }
    if (Objects.isNull(updatedEvent.getMileage())) {
      updatedEvent.setMileage(oldEvent.getMileage());
    }
    if (Objects.isNull(updatedEvent.getLocation())) {
      updatedEvent.setLocation(oldEvent.getLocation());
    }
    if (Objects.isNull(updatedEvent.getCompany())) {
      updatedEvent.setCompany(oldEvent.getCompany());
    }
    if (Objects.isNull(updatedEvent.getDescription())) {
      updatedEvent.setDescription(oldEvent.getDescription());
    }

    maintEventDao.updateMaintEvent(updatedEvent);

  }

  public void deleteEvent(String authToken, Integer vehicleID, Integer eventID)
          throws UnauthorizedException, NotFoundException {
    MaintEvent maintEvent=this.getEvent(authToken, vehicleID, eventID);
    maintEventDao.deleteMaintEvent(eventID);
  }


}
