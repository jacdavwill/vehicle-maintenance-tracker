package com.example.dataAccess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.model.MaintEvent;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@SpringBootTest
@ContextConfiguration(classes=VehicleMaintenanceTrackerApplication.class)
@ActiveProfiles(profiles = "test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class MaintEventDaoTest {

  @Autowired
  DbGenerator dbGenerator;
  @Autowired
  MaintEventDao maintEventDao;

  @BeforeAll
  void setUp(){
    dbGenerator.dropTables();
    dbGenerator.createTables();
    try {
      dbGenerator.createDummyData("src/main/resources/dummyData.txt");
    } catch(Exception e){
      log.error("Problem with the Dummy Data file!", e);
    }
  }

  @Test
  void retrieveMaintEvent() {
    MaintEvent maintEvent = maintEventDao.retrieveMaintEvent(1);
    MaintEvent expected = new MaintEvent(1, 1, LocalDate.parse("2020-10-18"), 101199, "Orem, Utah", "Jiffy Lube", "Oil change by the fine people at Jiffy Lube");
    assertThat(maintEvent).isEqualTo(expected);
  }

  @Test
  void retrieveMaintEvent_DoesntExist() {
    assertThat(maintEventDao.retrieveMaintEvent(20)).isNull();
  }

  @Test
  void createMaintEvent() {
    MaintEvent maintEvent = new MaintEvent(0, 1, LocalDate.parse("2020-10-25"), 103293, "Sandy, Utah", "Discount Tire Co.", "Fixed tires");
    int maintEventId = maintEventDao.createMaintEvent(maintEvent);
    assertThat(maintEventId).isEqualTo(5);
    MaintEvent result = maintEventDao.retrieveMaintEvent(5);
    MaintEvent expected = new MaintEvent(5, 1, LocalDate.parse("2020-10-25"), 103293, "Sandy, Utah", "Discount Tire Co.", "Fixed tires");
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void createMaintEvent_InvalidMaintItemId() {
    MaintEvent maintEvent = new MaintEvent(3, 20, LocalDate.parse("2020-10-25"), 103293, "Sandy, Utah", "Discount Tire Co.", "Fixed tires");
    assertThatThrownBy(()->maintEventDao.createMaintEvent(maintEvent)).isInstanceOf(
        DataAccessException.class);
  }

  @Test
  void deleteMaintEvent() {
    MaintEvent maintEvent = new MaintEvent(3, 1, LocalDate.parse("2020-10-25"), 103293, "Sandy, Utah", "Discount Tire Co.", "Fixed tires");
    int maintEventId = maintEventDao.createMaintEvent(maintEvent);
    maintEventDao.deleteMaintEvent(maintEventId);
    assertThat(maintEventDao.retrieveMaintEvent(maintEventId)).isNull();
  }

  @Test
  void deleteMaintEvent_InvalidMaintEventId_ExceptionNotThrown() {
    maintEventDao.deleteMaintEvent(20);
  }

  @Test
  void updateMaintEvent() {
    MaintEvent updatedMaintEvent = new MaintEvent(1, 1, LocalDate.parse("2020-10-25"), 103293, "Updated, Utah", "Discount Tire Co.", "Updated tires");
    maintEventDao.updateMaintEvent(updatedMaintEvent);
    MaintEvent result = maintEventDao.retrieveMaintEvent(1);
    assertThat(result).isEqualTo(updatedMaintEvent);
  }

  @Test
  void updateMaintEvent_InvalidMaintEventId_JustDoesntDoAnything() {
    MaintEvent updatedMaintEvent = new MaintEvent(20, 1, LocalDate.parse("2020-10-25"), 103293, "Updated, Utah", "Discount Tire Co.", "Updated tires");
    maintEventDao.updateMaintEvent(updatedMaintEvent); // this does not throw an error - it just won't modify the db at all
    assertThat(maintEventDao.retrieveMaintEvent(20)).isNull();
  }

  @Test
  void retrieveMaintEvents() {
    MaintEvent item1 = new MaintEvent(1, 1, LocalDate.parse("2020-10-18"), 101199, "Orem, Utah", "Jiffy Lube", "Oil change by the fine people at Jiffy Lube");
    MaintEvent item2 = new MaintEvent(3, 3, LocalDate.parse("2020-10-25"), 103230, "Orem, Utah", "Jiffy Lube", "Tires rotated by the fine Jiffy Lubers");
    MaintEvent item3 = new MaintEvent(4, 1, LocalDate.parse("2020-10-25"), 103230, "Orem, Utah", "Jiffy Lube", "Oil change (1 week later) by the fine people at Jiffy Lube");

    List<MaintEvent> maintEvents = maintEventDao.retrieveMaintEvents(1);
    assertThat(maintEvents).isEqualTo(Arrays.asList(item1, item2, item3));
  }
}