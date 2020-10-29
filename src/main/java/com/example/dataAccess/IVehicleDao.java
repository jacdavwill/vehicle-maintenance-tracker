package com.example.dataAccess;

import com.example.model.Vehicle;
import java.util.List;

public interface IVehicleDao {
  Vehicle retrieveVehicle(int vehicleId);
  Integer createVehicle(Vehicle newVehicle);
  void deleteVehicle(int vehicleId);
  void updateVehicle(Vehicle updatedVehicle);

  List<Vehicle> retrieveVehicles(int userId);
}
