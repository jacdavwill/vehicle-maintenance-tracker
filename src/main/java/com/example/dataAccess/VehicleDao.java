package com.example.dataAccess;

import com.example.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDao implements IVehicleDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Vehicle retrieveVehicle(int vehicleId) {
        String GET_VEHICLE = "SELECT * FROM vehicle WHERE vehicle_id = ?";
        return jdbc.queryForObject(GET_VEHICLE, new Object[]{vehicleId}, new BeanPropertyRowMapper<>(Vehicle.class));
    }

    @Override
    public void createVehicle(Vehicle newVehicle) {
//        try {
//
//
//
//        } catch (SQLException e) {
//            // TODO: Throw exception
//            System.out.println(e.toString());
//        }
    }

    @Override
    public void deleteVehicle(String vehicleId) {

    }

    @Override
    public void updateVehicle(Vehicle updatedVehicle) {

    }

    @Override
    public List<Vehicle> retrieveVehicles(int userId) {
        return null;
    }
}
