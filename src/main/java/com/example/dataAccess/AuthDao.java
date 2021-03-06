package com.example.dataAccess;

import com.example.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            return jdbc.queryForObject(GET_AUTH, new Object[]{userId}, new BeanPropertyRowMapper<>(Auth.class));
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Auth retrieveAuth(String authToken) {
        String GET_AUTH = "SELECT * FROM auth WHERE auth_token = ?";
        try {
            return jdbc.queryForObject(GET_AUTH, new Object[]{authToken}, new BeanPropertyRowMapper<>(Auth.class));
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String createAuth(Auth auth) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String INSERT_AUTH = "INSERT INTO auth VALUES (:userId, :authToken, :createTime)";
        parameterJdbc.update(INSERT_AUTH, new BeanPropertySqlParameterSource(auth), keyHolder);
        if (keyHolder.getKeys() != null && keyHolder.getKeys().containsKey("auth_token")) {
            return (String) keyHolder.getKeys().get("auth_token");
        }
        return null;
    }

    @Override
    public void deleteAuth(Integer userId) {
        String DELETE_AUTH = "DELETE FROM auth WHERE user_id = ?";
        jdbc.update(DELETE_AUTH, userId);
    }

    @Override
    public void deleteAuth(String authToken) {
        String DELETE_AUTH = "DELETE FROM auth WHERE auth_token = ?";
        jdbc.update(DELETE_AUTH, authToken);
    }

    @Override
    public void updateAuth(Auth updatedAuth) {
        String UPDATE_AUTH = "UPDATE auth SET auth_token = :authToken " +
                "WHERE user_id = :userId";
        parameterJdbc.update(UPDATE_AUTH, new BeanPropertySqlParameterSource(updatedAuth));
    }
}
