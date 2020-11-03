package com.example.dataAccess;

import com.example.model.User;
import com.example.model.Vehicle;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes= VehicleMaintenanceTrackerApplication.class)
class VehicleDaoTest {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JdbcTemplate jdbc;

    private Integer userId;

    Vehicle generateVehicle() {
        return new Vehicle(1, 1, "LadyBug", "http://google.com", "NOV",
                138473, "Hyundai", "Accent", 2007, "Maroon", "Automatic", "Gas");
    }

    @BeforeEach
    void setUp() {
        userId = userDao.createUser(new User(0, "email", "password", "salt", "displayName", "phone"));
    }

    @AfterEach
    void tearDown() {
        jdbc.update("DELETE FROM vehicle WHERE user_id = ?", userId);
        userDao.deleteUser(userId);
    }

    @Test
    void vehicleCrud() {
        Vehicle vehicle = this.generateVehicle();
        Integer vehicleId = this.vehicleDao.createVehicle(vehicle);
        vehicle.setVehicleId(vehicleId);
        Vehicle fetchedVehicle = this.vehicleDao.retrieveVehicle(vehicleId);
        assertThat(vehicle).isEqualTo(fetchedVehicle);

        vehicle.setNickname("Ladybug");
        vehicle.setImageUrl("other url");
        vehicle.setRegistrationMonth("DEC");
        vehicle.setMileage(123);
        vehicle.setMake("Buick");
        vehicle.setModel("Lucerne");
        vehicle.setYear(2006);
        vehicle.setColor("Silver");
        vehicle.setTransmissionType("Manual");
        vehicle.setEnergyType("Electric");
        this.vehicleDao.updateVehicle(vehicle);
        fetchedVehicle = this.vehicleDao.retrieveVehicle(vehicleId);
        assertThat(vehicle).isEqualTo(fetchedVehicle);

        this.vehicleDao.deleteVehicle(vehicleId);
        assertThatExceptionOfType(EmptyResultDataAccessException.class).isThrownBy(() -> {this.vehicleDao.retrieveVehicle(vehicleId);});
    }

    @Test
    void getMultipleVehicles() {
        Vehicle v1 = this.generateVehicle();
        Vehicle v2 = this.generateVehicle();
        Vehicle v3 = this.generateVehicle();

        v1.setVehicleId(this.vehicleDao.createVehicle(v1));
        v2.setVehicleId(this.vehicleDao.createVehicle(v2));
        v3.setVehicleId(this.vehicleDao.createVehicle(v3));

        List<Vehicle> allVehicles = this.vehicleDao.retrieveVehicles(userId);
        assertThat(allVehicles.size()).isEqualTo(3);
        assertThat(allVehicles).contains(v1, v2, v3);
    }
}