package com.example.dataAccess;

import com.example.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthDao implements IAuthDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private NamedParameterJdbcTemplate parameterJdbc;

    @Override
    public Auth retrieveAuth(Integer userId) {
        String GET_AUTH = "SELECT * FROM auth WHERE user_id = ?";
        return jdbc.queryForObject(GET_AUTH, new Object[]{userId}, new BeanPropertyRowMapper<>(Auth.class));
    }

    @Override
    public Auth retrieveAuth(String sessionKey) {
        String GET_AUTH = "SELECT * FROM auth WHERE session_key = ?";
        return jdbc.queryForObject(GET_AUTH, new Object[]{sessionKey}, new BeanPropertyRowMapper<>(Auth.class));
    }

    @Override
    public String createAuth(Auth auth) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String INSERT_AUTH = "INSERT INTO auth VALUES (:userId, :sessionKey, :createTime)";
        parameterJdbc.update(INSERT_AUTH, new BeanPropertySqlParameterSource(auth), keyHolder);
        if (keyHolder.getKeys() != null && keyHolder.getKeys().containsKey("session_key")) {
            return (String) keyHolder.getKeys().get("session_key");
        }
        return null;
//        throw new Exception("Error creating vehicle");
    }

    @Override
    public void deleteAuth(Integer userId) {
        String DELETE_AUTH = "DELETE FROM auth WHERE user_id = ?";
        jdbc.update(DELETE_AUTH, userId);
    }

    @Override
    public void deleteAuth(String sessionKey) {
        String DELETE_AUTH = "DELETE FROM auth WHERE session_key = ?";
        jdbc.update(DELETE_AUTH, sessionKey);
    }

    @Override
    public void updateAuth(Auth updatedAuth) {
        String UPDATE_AUTH = "UPDATE auth SET session_key = :sessionKey, created_time = :createdTime" +
                "WHERE user_id = :userId";
        parameterJdbc.update(UPDATE_AUTH, new BeanPropertySqlParameterSource(updatedAuth));
    }
}
