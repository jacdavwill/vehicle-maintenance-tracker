package com.example.dataAccess;

import com.example.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDao implements IVehicleDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private NamedParameterJdbcTemplate parameterJdbc;

    @Override
    public Vehicle retrieveVehicle(int vehicleId) {
        String GET_VEHICLE = "SELECT * FROM vehicle WHERE vehicle_id = ?";
        return jdbc.queryForObject(GET_VEHICLE, new Object[]{vehicleId}, new BeanPropertyRowMapper<>(Vehicle.class));
    }

    @Override
    public Integer createVehicle(Vehicle vehicle) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String INSERT_VEHICLE = "INSERT INTO vehicle (user_id, nickname, image_url, registration_month, mileage, " +
                "make, model, year, color, transmission_type, energy_type) " +
                "VALUES (:userId, :nickname, :imageUrl, :registrationMonth, :mileage, :make, :model, :year, :color, " +
                ":transmissionType, :energyType) ";
        parameterJdbc.update(INSERT_VEHICLE, new BeanPropertySqlParameterSource(vehicle), keyHolder);
        if (keyHolder.getKeys() != null && keyHolder.getKeys().containsKey("vehicle_id")) {
            return (Integer) keyHolder.getKeys().get("vehicle_id");
        }
        return null;
//        throw new Exception("Error creating vehicle");
    }

    @Override
    public void deleteVehicle(int vehicleId) {
        String DELETE_VEHICLE = "DELETE FROM vehicle WHERE vehicle_id = ?";
        jdbc.update(DELETE_VEHICLE, vehicleId);
    }

    @Override
    public void updateVehicle(Vehicle updatedVehicle) {
        String UPDATE_VEHICLE = "UPDATE vehicle SET nickname = :nickname, image_url = :imageUrl, " +
                "registration_month = :registrationMonth, mileage = :mileage, make = :make, model = :model, " +
                "year = :year, color = :color, transmission_type = :transmissionType, energy_type = :energyType " +
                "WHERE vehicle_id = :vehicleId";
        parameterJdbc.update(UPDATE_VEHICLE, new BeanPropertySqlParameterSource(updatedVehicle));
    }

    @Override
    public List<Vehicle> retrieveVehicles(int userId) {
        String GET_VEHICLES = "SELECT * FROM vehicle WHERE user_id = ?";
        return jdbc.query(GET_VEHICLES, new Object[]{userId}, new BeanPropertyRowMapper<>(Vehicle.class));
    }
}
