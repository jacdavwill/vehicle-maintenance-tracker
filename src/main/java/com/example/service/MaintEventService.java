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

//TODO: Should Events be tied to Items or to Vehicles, currently its tied to vehicles
@Service
public class MaintEventService extends com.example.service.Service {

  @Autowired
  MaintEventDao maintEventDao;

  public List<MaintEvent> getAllEvents(String authToken, Integer vehicleID)
          throws UnauthorizedException, NotFoundException {
    this.validateRequest(authToken, vehicleID);
    return maintEventDao.retrieveMaintEvents(vehicleID);
  }

  // Might add check to see if vehicle is associated with user.
  public MaintEvent getEvent(String authToken, Integer vehicleID, Integer eventID)
          throws UnauthorizedException, NotFoundException {
    this.validateRequest(authToken, vehicleID);
    MaintEvent maintEvent=maintEventDao.retrieveMaintEvent(eventID);
    if (maintEvent == null) {
      throw new NotFoundException("MaintEvent not found");
    }
    return maintEvent;
  }

  public Integer addEvent(String authToken, Integer vehicleID, MaintEvent newEvent)
          throws UnauthorizedException, NotFoundException {
    this.validateRequest(authToken, vehicleID);
    this.checkValidItemId(newEvent.getMaintItemId(), vehicleID);
    return maintEventDao.createMaintEvent(newEvent);
  }

  // TODO: Is itemID one of the fields to edit?
  public void updateEvent(String authToken, Integer vehicleID, Integer eventID, MaintEvent updatedEvent)
          throws UnauthorizedException, NotFoundException {
    MaintEvent oldEvent=this.getEvent(authToken, vehicleID, eventID);
    updatedEvent.setMaintEventId(eventID);
    updatedEvent.setMaintItemId(oldEvent.getMaintItemId());
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
    validateRequest(authToken, vehicleID);
    MaintEvent maintEvent=this.getEvent(authToken, vehicleID, eventID);
    maintEventDao.deleteMaintEvent(eventID);
  }

  private void validateRequest(String authToken, Integer vehicleId) throws UnauthorizedException, NotFoundException {
    int userId = this.getUserIdFromAuthToken(authToken);
    this.checkValidVehicle(userId, vehicleId);
  }

}
