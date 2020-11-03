package com.example.dataAccess;

import static org.junit.jupiter.api.Assertions.*;

import com.example.model.MaintItem;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes= VehicleMaintenanceTrackerApplication.class)
class MaintItemDaoTest {

  @Autowired
  MaintItemDao maintItemDao;
  
  @Test
  void retrieveMaintItem() {
    MaintItem maintItem = maintItemDao.retrieveMaintItem(1);
    System.out.println("MaintItem description: " + maintItem.getDescription());
  }

  @Test
  void createMaintItem() {
    //TODO: need to make sure this maint item exists in db
    MaintItem maintItem = new MaintItem(null, 1, 6, null, "Oil change every 6 months", new Date(), 135283);
    int maintItemId = maintItemDao.createMaintItem(maintItem);
    System.out.println("MaintItem id: " +  maintItemId);
  }

  @Test
  void deleteMaintItem() {
    maintItemDao.deleteMaintItem(3);
  }

  @Test
  void updateMaintItem() {
    MaintItem updatedMaintItem = new MaintItem(1, 1, 7, null, "Oil change every 7 months now", new Date(), 38293);
    maintItemDao.updateMaintItem(updatedMaintItem);
  }

  @Test
  void retrieveMaintItems() {
    List<MaintItem> maintItems = maintItemDao.retrieveMaintItems(1);
    System.out.println("Number of maintItems: " + maintItems.size());
  }
}