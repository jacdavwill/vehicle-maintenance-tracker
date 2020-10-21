package com.example.dataAccess;

import com.example.model.MaintItem;
import java.util.List;

public interface IMaintItem {
  MaintItem retrieveMaintItem(int maintItemId);
  void createMaintItem(MaintItem newMaintItem);
  void deleteMaintItem(int maintItemId);
  void updateMaintItem(MaintItem updatedMaintItem);

  List<MaintItem> retrieveMaintItems(int vehicleId);
}
