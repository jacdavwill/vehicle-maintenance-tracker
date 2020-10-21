package com.example.dataAccess;

import com.example.model.MaintEvent;
import java.util.List;

public interface IMaintEvent {
  MaintEvent retrieveMaintEvent(int maintEventId);
  void createMaintEvent(MaintEvent newMaintEvent);
  void deleteMaintEvent(int maintEventId);
  void updateMaintEvent(MaintEvent updatedMaintEvent);
  
  List<MaintEvent> retrieveMaintEvents(int vehicleId);
}
