package com.example.dataAccess;

import com.example.model.MaintItem;
import java.util.List;

public interface IMaintItemDao {
  MaintItem retrieveMaintItem(Integer maintItemId);
  Integer createMaintItem(MaintItem newMaintItem);
  void deleteMaintItem(Integer maintItemId);
  void updateMaintItem(MaintItem updatedMaintItem);

  List<MaintItem> retrieveMaintItems(Integer vehicleId);
  List<MaintItem> retrieveMaintItemsDueForNotification();
}
