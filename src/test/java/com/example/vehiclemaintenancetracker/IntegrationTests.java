package com.example.vehiclemaintenancetracker;

import com.example.dataAccess.AuthDao;
import com.example.dataAccess.UserDao;
import com.example.dataAccess.VehicleDao;
import com.example.model.Auth;
import com.example.model.User;
import com.example.model.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes= VehicleMaintenanceTrackerApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthDao authDao;

    @Autowired
    private VehicleDao vehicleDao;

    private final String USER_EMAIL = "user@example.com";
    private final String USER_PASSWORD = "dkvgtsmrnd76owhdct";
    private final String USER_NAME = "John Doe";

    private Auth authToken;
    private Vehicle vehicle;
    private Integer userId;

    @BeforeAll
    void setup() {
        User user = userDao.retrieveUser(USER_EMAIL);
        if (user != null) {
            vehicleDao.retrieveVehicles(user.getUserId()).forEach(v -> vehicleDao.deleteVehicle(v.getVehicleId()));
            authDao.deleteAuth(user.getUserId());
            userDao.deleteUser(user.getUserId());
        }
    }

    @Test
    @Order(1)
    void validUserRegistrationShouldReturn200Success() throws Exception {
        User user = new User(USER_EMAIL, USER_PASSWORD, USER_NAME);

        MvcResult result = mockMvc.perform(post("/api/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();

        authToken = objectMapper.readValue(result.getResponse().getContentAsString(), Auth.class);
        assertThat(authToken.getAuthToken()).isNotNull();
        userId = this.userDao.retrieveUser(USER_EMAIL).getUserId();
    }

    @Test
    @Order(2)
    void userIsAlreadyRegisteredShouldReturn400Error() throws Exception {
        User user = new User(USER_EMAIL, USER_PASSWORD, USER_NAME);

        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void userAddingValidVehicleShouldReturn200Success() throws Exception {
        vehicle = new Vehicle();
        vehicle.setNickname("nickname");
        vehicle.setImageUrl("url");
        vehicle.setRegistrationMonth("NOV");
        vehicle.setMileage(2387);
        vehicle.setMake("Hyundai");
        vehicle.setModel("Accent");
        vehicle.setYear(2007);
        vehicle.setColor("Maroon");
        vehicle.setTransmissionType("Automatic");

        MvcResult result = mockMvc.perform(post("/api/vehicles")
            .header("authToken", authToken.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(vehicle)))
                .andExpect(status().isOk())
                .andReturn();

        Integer vehicleId = objectMapper.readValue(result.getResponse().getContentAsString(), Vehicle.class).getVehicleId();
        assertThat(vehicleId).isNotNull();
        vehicle.setVehicleId(vehicleId);
        vehicle.setUserId(userId);
    }

    @Test
    @Order(4)
    void fetchingListOfVehiclesForUserShouldReturn200Success() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/vehicles")
            .header("authToken", authToken.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<Vehicle> vehicles = Arrays.stream(objectMapper.readValue(result.getResponse().getContentAsString(), Vehicle[].class))
                .collect(Collectors.toList());
        assertThat(vehicles.size()).isEqualTo(1);
        assertThat(vehicles.get(0)).isEqualTo(vehicle);
    }

    @Test
    @Order(5)
    void userCanLogoutAndLogBackIn() throws Exception {
        mockMvc.perform(delete("/api/user/login")
            .header("authToken", authToken.getAuthToken()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/vehicles")
            .header("authToken", authToken.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        User user = new User(USER_EMAIL, "Wrong password");
        mockMvc.perform(post("/api/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isUnauthorized())
                .andReturn();

        user.setPassword(USER_PASSWORD);
        MvcResult result = mockMvc.perform(post("/api/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();

        authToken = objectMapper.readValue(result.getResponse().getContentAsString(), Auth.class);
        assertThat(authToken.getAuthToken()).isNotNull();
    }
}
