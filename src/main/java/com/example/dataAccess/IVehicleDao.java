package com.example.dataAccess;

import com.example.model.User;
import com.example.model.Vehicle;
import java.util.List;

public interface IVehicleDao {
  Vehicle retrieveVehicle(int vehicleId);
  void createVehicle(Vehicle newVehicle);
  void deleteVehicle(String vehicleId);
  void updateVehicle(Vehicle updatedVehicle);

  List<Vehicle> retrieveVehicles(int userId);
}
