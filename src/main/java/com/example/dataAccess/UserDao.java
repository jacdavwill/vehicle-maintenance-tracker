package com.example.dataAccess;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class UserDao implements IUserDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private NamedParameterJdbcTemplate parameterJdbc;

    @Override
    public User retrieveUser(Integer userId) {
        String GET_USER = "SELECT * FROM users WHERE user_id = ?";
        return jdbc.queryForObject(GET_USER, new Object[]{userId}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Integer createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String INSERT_USER = "INSERT INTO users (email, password, salt, display_name, phone) " +
                "VALUES (:email, :password, :salt, :displayName, :phone) ";
        parameterJdbc.update(INSERT_USER, new BeanPropertySqlParameterSource(user), keyHolder);
        if (keyHolder.getKeys() != null && keyHolder.getKeys().containsKey("user_id")) {
            return (Integer) keyHolder.getKeys().get("user_id");
        }
        return null;
//        throw new Exception("Error creating vehicle");
    }

    @Override
    public void deleteUser(Integer userId) {
        String DELETE_USER = "DELETE FROM users WHERE user_id = ?";
        jdbc.update(DELETE_USER, userId);
    }

    @Override
    public void updateUser(User updatedUser) {
        String UPDATE_USER = "UPDATE users SET email = :email, password = :password, salt = :salt, " +
                "display_name = :displayName, phone = :phone WHERE user_id = :userId";
        parameterJdbc.update(UPDATE_USER, new BeanPropertySqlParameterSource(updatedUser));
    }
}
