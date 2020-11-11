package com.example.dataAccess;

import com.example.model.User;
import com.example.vehiclemaintenancetracker.VehicleMaintenanceTrackerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes= VehicleMaintenanceTrackerApplication.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private User user;

    @Test
    void userCrud() {
        user = new User(0, "email@gmail.com", "password", "salt", "displayName", "phone");
        Integer userId = userDao.createUser(user);
        user.setUserId(userId);
        User fetchById = userDao.retrieveUser(user.getUserId());
        User fetchByEmail = userDao.retrieveUser(user.getEmail());
        assertThat(user).isEqualTo(fetchById);
        assertThat(user).isEqualTo(fetchByEmail);

        user.setDisplayName("John Doe");
        user.setEmail("example@google.com");
        user.setPassword("hash$873598347");
        user.setSalt("swagger12");
        user.setPhone("555-555-5555");
        userDao.updateUser(user);
        fetchById = userDao.retrieveUser(user.getUserId());
        assertThat(user).isEqualTo(fetchById);

        userDao.deleteUser(user.getUserId());
        assertThat(userDao.retrieveUser(user.getUserId())).isNull();
    }

}
