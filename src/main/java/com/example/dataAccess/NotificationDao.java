package com.example.dataAccess;

import com.example.model.Notification;
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
public class NotificationDao implements INotificationDao {

  @Autowired
  NamedParameterJdbcTemplate namedJdbc;

  @Override
  public Notification retrieveNotification(Integer notificationId) {
    final String SQL = "SELECT * FROM notification WHERE notification_id = :notificationId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("notificationId", notificationId);
    try {
      return namedJdbc
          .queryForObject(SQL, sqlParameterSource, new BeanPropertyRowMapper<>(Notification.class));
    } catch(EmptyResultDataAccessException e){
      log.warn("Could not retrieve notification id {}", notificationId);
      return null;
    }
  }

  //Assuming for now that there is only one notification per maintItemId at a time
  @Override
  public Notification retrieveNotificationForMaintItemId(Integer maintItemId) {
    final String SQL = "SELECT * FROM notification WHERE maint_item_id = :maintItemId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("maintItemId", maintItemId);
    try {
      return namedJdbc
          .queryForObject(SQL, sqlParameterSource, new BeanPropertyRowMapper<>(Notification.class));
    } catch(EmptyResultDataAccessException e){
      return null;
    }
  }

  @Override
  public Integer createNotification(Notification newNotification) {
    final String SQL = "INSERT INTO notification (maint_item_id, display_message) VALUES (:maintItemId, :displayMessage)";
    SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(newNotification);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedJdbc.update(SQL, sqlParameterSource, keyHolder);
    return (Integer)keyHolder.getKeys().get("notification_id");
  }

  @Override
  public void deleteNotification(Integer notificationId) {
    final String SQL = "DELETE FROM notification WHERE notification_id = :notificationId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("notificationId", notificationId);
    namedJdbc.update(SQL, sqlParameterSource);
  }

  // This expects the updatedNotification to have all Notification fields, not just the ones being updated
  @Override
  public void updateNotification(Notification updatedNotification) {
    final String SQL = "UPDATE notification SET maint_item_id = :maintItemId, display_message = :displayMessage WHERE notification_id = :notificationId";
    SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(updatedNotification);
    namedJdbc.update(SQL, sqlParameterSource);
  }

  @Override
  public List<Notification> retrieveNotifications(Integer userId) {
    final String SQL = "SELECT n.notification_id, n.maint_item_id, n.display_message FROM notification n JOIN maint_item m ON n.maint_item_id = m.maint_item_id JOIN vehicle v ON m.vehicle_id = v.vehicle_id WHERE user_id = :userId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("userId", userId);
    return namedJdbc.query(SQL, sqlParameterSource, new BeanPropertyRowMapper<>(Notification.class));
  }
}
