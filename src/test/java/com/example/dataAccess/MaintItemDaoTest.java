package com.example.dataAccess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.model.MaintItem;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
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
@Execution(ExecutionMode.SAME_THREAD)
class MaintItemDaoTest {

  @Autowired
  DbGenerator dbGenerator;
  @Autowired
  MaintItemDao maintItemDao;

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
  void retrieveMaintItem() {
    MaintItem maintItem = maintItemDao.retrieveMaintItem(1);
    MaintItem expected = new MaintItem(1, 1, 6, 0, "Oil change every 6 months", LocalDate.parse("2020-07-15"), 100036);
    assertThat(maintItem).isEqualTo(expected);
  }

  @Test
  void retrieveMaintItem_DoesntExist() {
    assertThat(maintItemDao.retrieveMaintItem(20)).isNull();
  }

  @Test
  void createMaintItem() {
    MaintItem maintItem = new MaintItem(0, 2, 5, 0, "Oil change every 5 months", LocalDate.now(), 382953);
    int maintItemId = maintItemDao.createMaintItem(maintItem);
    MaintItem result = maintItemDao.retrieveMaintItem(maintItemId);
    maintItem.setMaintItemId(maintItemId); //Don't care what the id is, just want to check other fields
    assertThat(result).isEqualTo(maintItem);
  }

  @Test
  void createMaintItem_InvalidVehicleId() {
    MaintItem maintItem = new MaintItem(0, 20, 5, 0, "Oil change every 5 months", LocalDate.now(), 382953);
    assertThatThrownBy(()->maintItemDao.createMaintItem(maintItem)).isInstanceOf(
        DataAccessException.class);
  }

  @Test
  void createMaintItem_NullFields() {
    MaintItem maintItem = new MaintItem(0, 2, null, null, null, null, null);
    int maintItemId = maintItemDao.createMaintItem(maintItem);
    MaintItem result = maintItemDao.retrieveMaintItem(maintItemId);
    maintItem.setMaintItemId(maintItemId); //Don't really care what the id is, just want to make sure other fields are same
    assertThat(result).isEqualTo(maintItem);
  }

  @Test
  void deleteMaintItem() {
    MaintItem maintItem = new MaintItem(0, 2, 5, 0, "Oil change every 5 months", LocalDate.now(), 382953);
    int maintItemId = maintItemDao.createMaintItem(maintItem);
    maintItemDao.deleteMaintItem(maintItemId);
    assertThat(maintItemDao.retrieveMaintItem(maintItemId)).isNull();
  }

  @Test
  void deleteMaintItem_InvalidMaintItemId_ExceptionNotThrown() {
    maintItemDao.deleteMaintItem(20);
  }

  @Test
  void updateMaintItem() {
    MaintItem updatedMaintItem = new MaintItem(1, 1, 4, 0, "Oil change every 4 months", LocalDate.now(), 132953);
    maintItemDao.updateMaintItem(updatedMaintItem);
    MaintItem result = maintItemDao.retrieveMaintItem(1);
    assertThat(result).isEqualTo(updatedMaintItem);
  }

  @Test
  void updateMaintItem_InvalidMaintItemId_JustDoesntDoAnything() {
    MaintItem updatedMaintItem = new MaintItem(20, 1, 4, 0, "Oil change every 4 months", LocalDate.now(), 132953);
    maintItemDao.updateMaintItem(updatedMaintItem); // this does not throw an error - it just won't modify the db at all
    assertThat(maintItemDao.retrieveMaintItem(20)).isNull();
  }

  @Test
  void retrieveMaintItems() {
    MaintItem item1 = new MaintItem(1, 1, 6, 0, "Oil change every 6 months", LocalDate.parse("2020-07-15"), 100036);
    MaintItem item2 = new MaintItem(3, 1, 0, 5000, "Tire rotation every 5000 miles", LocalDate.parse("2020-06-26"), 99382);

    List<MaintItem> maintItems = maintItemDao.retrieveMaintItems(1);
    assertThat(maintItems).isEqualTo(Arrays.asList(item1, item2));
  }

  @Test
  void retrieveMaintItemsDueForNotification() {
    MaintItem item1 = new MaintItem(2, 2, 0, 5000, "Tire rotation every 5000 miles", LocalDate.parse("2020-07-29"), 34253);
    MaintItem item2 = new MaintItem(4, 3, 6, 0, "Tire Change every 6 months", LocalDate.parse("2020-05-29"), 29323);

    List<MaintItem> maintItems = maintItemDao.retrieveMaintItemsDueForNotification();
    assertThat(maintItems).isEqualTo(Arrays.asList(item1, item2));
  }

  @Test
  void retrieveMaintItemsDueForNotification_WithZeroValues_DoesntReturn() {
    MaintItem item1 = new MaintItem(0, 1, 0, 0, "Null fields", LocalDate.parse("2020-05-29"), 0);
    MaintItem item2 = new MaintItem(0, 1, 0, 0, "Tire Change every 6 months", LocalDate.parse("2020-05-29"), 0);

    maintItemDao.createMaintItem(item1);
    maintItemDao.createMaintItem(item2);
    List<MaintItem> maintItems = maintItemDao.retrieveMaintItemsDueForNotification();
    assertThat(maintItems.size()).isEqualTo(2);
  }
}