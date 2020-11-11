package com.example.dataAccess;

import com.example.model.MaintItem;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MaintItemDao implements IMaintItemDao {

  @Autowired
  NamedParameterJdbcTemplate namedJdbc;

  @Override
  public MaintItem retrieveMaintItem(Integer maintItemId) {
    final String SQL = "SELECT * FROM maint_item WHERE maint_item_id = :maintItemId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("maintItemId", maintItemId);
    try {
      return namedJdbc
          .queryForObject(SQL, sqlParameterSource, new BeanPropertyRowMapper<>(MaintItem.class));
    } catch(EmptyResultDataAccessException e){
      log.warn("Could not retrieve maint item id {}", maintItemId);
      return null;
    }
  }

  @Override
  public Integer createMaintItem(MaintItem newMaintItem) {
    final String SQL = "INSERT INTO maint_item (vehicle_id, frequency_months, frequency_miles, "
        + "description, last_completed_date, last_completed_mileage) VALUES (:vehicleId, "
        + ":frequencyMonths, :frequencyMiles, :description, :lastCompletedDate, :lastCompletedMileage)";
    SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(newMaintItem);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedJdbc.update(SQL, sqlParameterSource, keyHolder);
    return (Integer)keyHolder.getKeys().get("maint_item_id");
  }

  @Override
  public void deleteMaintItem(Integer maintItemId) {
    final String SQL = "DELETE FROM maint_item WHERE maint_item_id = :maintItemId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("maintItemId", maintItemId);
    namedJdbc.update(SQL, sqlParameterSource);
  }

  @Override
  public void updateMaintItem(MaintItem updatedMaintItem) {
    final String SQL = "UPDATE maint_item SET vehicle_id = :vehicleId, frequency_months = :frequencyMonths, "
        + "frequency_miles = :frequencyMiles, description = :description, last_completed_date = :lastCompletedDate, "
        + "last_completed_mileage = :lastCompletedMileage WHERE maint_item_id = :maintItemId";
    SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(updatedMaintItem);
    namedJdbc.update(SQL, sqlParameterSource);
  }

  @Override
  public List<MaintItem> retrieveMaintItems(Integer vehicleId) {
    final String SQL = "SELECT * FROM maint_item WHERE vehicle_id = :vehicleId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("vehicleId", vehicleId);
    return namedJdbc.query(SQL, sqlParameterSource, new BeanPropertyRowMapper<>(MaintItem.class));
  }
}
