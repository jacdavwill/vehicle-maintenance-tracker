package com.example.api;


import com.example.exceptions.InternalServiceException;
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

@RestController
public class UserAccountController {
	@Autowired
	ServiceFacade serviceFacade;

	Auth authToken = new Auth(1, "80213a79-ba1a-4f8e-a2f8-968cb7972d19", Date.from(Instant.now()));

	@PostMapping("/api/user/register")
	public ResponseEntity<Auth> register(@RequestBody User user) {
		try {
			serviceFacade.register(user);
			return new ResponseEntity<Auth>(authToken, HttpStatus.OK);

		} catch (InternalServiceException e) {
			//return new ResponseEntity<Auth>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return null;
		} catch (UnauthorizedException e) {
			//return new ResponseEntity<Auth>(null, HttpStatus.UNAUTHORIZED);
			return null;
		}

	}
	
	@PostMapping("/api/user/login")
	public ResponseEntity<Auth> login(@RequestBody User user) {
		try {
			serviceFacade.login(user);
			return new ResponseEntity<Auth>(authToken, HttpStatus.OK);

		} catch (InternalServiceException e) {
			//return new ResponseEntity<Auth>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return null;
		} catch (UnauthorizedException e) {
			//return new ResponseEntity<Auth>(null, HttpStatus.UNAUTHORIZED);
			return null;
		}

	}
	
	@DeleteMapping("/api/user/login")
	public ResponseEntity<String> logout(@RequestHeader String authToken) {
		serviceFacade.invalidateAuthToken(authToken);

		return new ResponseEntity<String>("This was a DELETE login API call", HttpStatus.OK);
	}
	
	@PostMapping("/api/user/reset")
	public ResponseEntity<String> reset(@RequestHeader String email) {
		serviceFacade.requestReset(email);

		return new ResponseEntity<String>("This was a POST reset API call", HttpStatus.OK);
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
