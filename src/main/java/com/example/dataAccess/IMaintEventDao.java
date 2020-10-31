package com.example.dataAccess;

import com.example.model.MaintEvent;
import java.util.List;

public interface IMaintEventDao {
  MaintEvent retrieveMaintEvent(Integer maintEventId);
  Integer createMaintEvent(MaintEvent newMaintEvent);
  void deleteMaintEvent(Integer maintEventId);
  void updateMaintEvent(MaintEvent updatedMaintEvent);
  
  List<MaintEvent> retrieveMaintEvents(Integer vehicleId);
}
