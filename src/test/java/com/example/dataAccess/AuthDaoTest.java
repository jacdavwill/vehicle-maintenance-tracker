package com.example.dataAccess;

import com.example.model.Auth;
import com.example.model.User;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes= VehicleMaintenanceTrackerApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthDaoTest {

    @Autowired
    AuthDao authDao;

    @Autowired
    UserDao userDao;

    private Integer userId;
    private final String authToken = "ZJMiXh5TEgI[q)qV5d)r";

    @BeforeAll
    void setUp() {
        User existingUser = userDao.retrieveUser("email");
        if (existingUser != null) {
            authDao.deleteAuth(existingUser.getUserId());
            userDao.deleteUser(existingUser.getUserId());
        }
        userId = userDao.createUser(new User(0, "email", "password", "salt", "displayName", "phone"));
    }

    @AfterAll
    void tearDown() {
        userDao.deleteUser(userId);
        authDao.deleteAuth(userId);
    }

    @Test
    void authCrud() {
        Auth auth = new Auth(userId, authToken);
        authDao.createAuth(auth);
        Auth fetchByUser = authDao.retrieveAuth(userId);
        Auth fetchByToken = authDao.retrieveAuth(authToken);
        assertThat(auth).isEqualTo(fetchByUser);
        assertThat(auth).isEqualTo(fetchByToken);

        auth.setAuthToken("otherToken");
        authDao.updateAuth(auth);
        fetchByUser = authDao.retrieveAuth(userId);
        assertThat(auth).isEqualTo(fetchByUser);

        authDao.deleteAuth(userId);
        assertThat(authDao.retrieveAuth(userId)).isNull();
    }


}
