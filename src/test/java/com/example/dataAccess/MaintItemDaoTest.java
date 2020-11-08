package com.example.dataAccess;

import static org.junit.jupiter.api.Assertions.*;

import com.example.model.MaintItem;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import java.util.Date;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@Log4j2
@SpringBootTest
@ContextConfiguration(classes= VehicleMaintenanceTrackerApplication.class)
class MaintItemDaoTest {

  @Autowired
  MaintItemDao maintItemDao;
  
  @Test
  void retrieveMaintItem() {
    MaintItem maintItem = maintItemDao.retrieveMaintItem(1);
    log.info("MaintItem description: " + maintItem.getDescription());
  }

  @Test
  void createMaintItem() {
    //TODO: need to make sure this maint item exists in db
    MaintItem maintItem = new MaintItem(0, 1, 6, 0, "Oil change every 6 months", new Date(), 135283);
    int maintItemId = maintItemDao.createMaintItem(maintItem);
    log.info("MaintItem id: " +  maintItemId);
  }

  @Test
  void deleteMaintItem() {
    maintItemDao.deleteMaintItem(3);
  }

  @Test
  void updateMaintItem() {
    MaintItem updatedMaintItem = new MaintItem(1, 1, 7, 0, "Oil change every 7 months now", new Date(), 38293);
    maintItemDao.updateMaintItem(updatedMaintItem);
  }

  @Test
  void retrieveMaintItems() {
    List<MaintItem> maintItems = maintItemDao.retrieveMaintItems(1);
    log.info("Number of maintItems: " + maintItems.size());
  }
}