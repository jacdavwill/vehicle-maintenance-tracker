package com.example.dataAccess;

import com.example.model.MaintEvent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class MaintEventDao implements IMaintEventDao {

  @Autowired
  NamedParameterJdbcTemplate namedJdbc;

  @Override
  public MaintEvent retrieveMaintEvent(Integer maintEventId) {
    final String SQL = "SELECT * FROM maint_event WHERE maint_event_id = :maintEventId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("maintEventId", maintEventId);
    return namedJdbc.queryForObject(SQL, sqlParameterSource, new BeanPropertyRowMapper<>(MaintEvent.class));
  }

  @Override
  public Integer createMaintEvent(MaintEvent newMaintEvent) {
    final String SQL = "INSERT INTO maint_event (maint_item_id, event_date, mileage, location, company, "
        + "description) VALUES (:maintItemId, :eventDate, :mileage, :location, :company, :description)";
    SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(newMaintEvent);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedJdbc.update(SQL, sqlParameterSource, keyHolder);
    return (Integer)keyHolder.getKeys().get("maint_event_id");
  }

  @Override
  public void deleteMaintEvent(Integer maintEventId) {
    final String SQL = "DELETE FROM maint_event WHERE maint_event_id = :maintEventId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("maintEventId", maintEventId);
    namedJdbc.update(SQL, sqlParameterSource);
  }

  @Override
  public void updateMaintEvent(MaintEvent updatedMaintEvent) {
    final String SQL = "UPDATE maint_event SET maint_item_id = :maintItemId, event_date = :eventDate, "
        + "mileage = :mileage, location = :location, company = :company, description = :description "
        + "WHERE maint_event_id = :maintEventId";
    SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(updatedMaintEvent);
    namedJdbc.update(SQL, sqlParameterSource);
  }

  @Override
  public List<MaintEvent> retrieveMaintEvents(Integer vehicleId) {
    final String SQL = "SELECT e.maint_event_id, e.maint_item_id, e.event_date, e.mileage, e.location, e.company, e.description FROM maint_event e JOIN maint_item i ON e.maint_item_id = i.maint_item_id WHERE vehicle_id = :vehicleId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("vehicleId", vehicleId);
    return namedJdbc.query(SQL, sqlParameterSource, new BeanPropertyRowMapper<>(MaintEvent.class));
  }
}
