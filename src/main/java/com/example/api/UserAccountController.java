package com.example.api;

import com.example.exceptions.AlreadyExistsException;
import com.example.exceptions.InternalServiceException;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Auth;
import com.example.model.User;
import com.example.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserAccountController {
	@Autowired
	ServiceFacade serviceFacade;

	@PostMapping("/api/user/register")
	public ResponseEntity<Map> register(@RequestBody User user) {
		try {
			String authToken = serviceFacade.register(user);
			HashMap<String, String> result = new HashMap<>();
    		result.put("authToken", authToken);
    		return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (InternalServiceException e) {
			HashMap<String, String> result = new HashMap<>();
    		result.put("error", e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AlreadyExistsException e) {
			HashMap<String, String> result = new HashMap<>();
    		result.put("error", e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

		} catch (UnauthorizedException e) {
			HashMap<String, String> result = new HashMap<>();
    		result.put("error", e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/api/user/login")
	public ResponseEntity<Map> login(@RequestBody User user) {
		try {
			String authToken = serviceFacade.login(user);
			HashMap<String, String> result = new HashMap<>();
    		result.put("authToken", authToken);
    		return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (InternalServiceException e) {
			HashMap<String, String> result = new HashMap<>();
    		result.put("error", e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		
		} catch (UnauthorizedException e) {
			HashMap<String, String> result = new HashMap<>();
    		result.put("error", e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
		}

	}
	
	@DeleteMapping("/api/user/login")
	public ResponseEntity<String> logout(@RequestHeader String authToken) {
		serviceFacade.invalidateAuthToken(authToken);
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@PostMapping("/api/user/reset")
	public ResponseEntity<Map> reset(@RequestHeader String email) {
		try {
			serviceFacade.requestReset(email);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (InternalServiceException e) {
			HashMap<String, String> result = new HashMap<>();
			result.put("error", e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			HashMap<String, String> result = new HashMap<>();
			result.put("error", e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/user")
	public ResponseEntity<String> updateUser(@RequestHeader String resetToken, @RequestHeader String authToken, 
	  @RequestBody User user) {
		try {
			serviceFacade.updateUser(resetToken, authToken, user);
			return new ResponseEntity<String>("This was a PUT user API call", HttpStatus.OK);

		} catch (InternalServiceException e) {
			//return new ResponseEntity<Auth>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return null;
		} catch (UnauthorizedException e) {
			//return new ResponseEntity<Auth>(null, HttpStatus.UNAUTHORIZED);
			return null;
		}

	}
}
