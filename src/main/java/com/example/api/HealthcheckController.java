package com.example.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthcheckController {

  @GetMapping("/api/healthcheck")
  public ResponseEntity<Map> getNotifications() {
    HashMap<String, String> result = new HashMap<>();
    result.put("status", "okay");
    return new ResponseEntity<Map>(result, HttpStatus.OK);
  }

}
