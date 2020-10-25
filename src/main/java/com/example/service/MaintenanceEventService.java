package com.example.service;

import com.example.model.*;

import java.util.List;

//TODO: Finish services
public class MaintenanceEventService extends Service {

    public List<MaintEvent> getAllEvents(String sessionKey, String vehicleID) {
        return null;
      }
    
      public MaintEvent getEvent(String sessionKey, String vehicleID, String eventID) {
        return null;
      }
    
      public String addEvent(String sessionKey, String vehicleID, MaintEvent newEvent) {
        return null;
      }
    
      public String updateEvent(String sessionKey, String vehicleID, String eventID, MaintEvent newEvent) {
        return null;
      }
    
      public String deleteEvent(String sessionKey, String vehicleID, String eventID) {
        return null;
      }

}
