package com.example.dataAccess;

import static org.junit.jupiter.api.Assertions.*;

import com.example.model.MaintEvent;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes= VehicleMaintenanceTrackerApplication.class)
class MaintEventDaoTest {

  @Autowired
  MaintEventDao maintEventDao;

  @Test
  void retrieveMaintEvent() {
    MaintEvent maintEvent = maintEventDao.retrieveMaintEvent(1);
    System.out.println("MaintEvent description: " + maintEvent.getDescription());
  }

  @Test
  void createMaintEvent() {
    //TODO: need to make sure this maint item exists in db
    MaintEvent maintEvent = new MaintEvent(null, 1, new Date(), 38293, "Pocatello", "Pep Boys", "replaced my tires");
    int maintEventId = maintEventDao.createMaintEvent(maintEvent);
    System.out.println("MaintEvent id: " +  maintEventId);
  }

  @Test
  void deleteMaintEvent() {
    maintEventDao.deleteMaintEvent(3);
  }

  @Test
  void updateMaintEvent() {
    MaintEvent updatedMaintEvent = new MaintEvent(2, 1, new Date(), 293829, "Chicago", "Jiffy Lube", "New oil change or something");
    maintEventDao.updateMaintEvent(updatedMaintEvent);
  }

  @Test
  void retrieveMaintEvents() {
    List<MaintEvent> maintEvents = maintEventDao.retrieveMaintEvents(1);
    System.out.println("Number of maintEvents: " + maintEvents.size());
  }
}