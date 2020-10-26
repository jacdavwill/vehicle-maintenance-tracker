package com.example.api;


import com.example.model.Auth;
import com.example.model.User;
import com.example.service.ServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;


@RestController
public class UserAccountController {

	Auth authToken = new Auth(1,"80213a79-ba1a-4f8e-a2f8-968cb7972d19", Date.from(Instant.now()));

	@PostMapping("/api/user/register")
	public ResponseEntity<Auth> register(@RequestBody User user) {
		ServiceFacade.register(user);

		return new ResponseEntity<Auth>(authToken, HttpStatus.OK);
	}
	
	@PostMapping("/api/user/login")
	public ResponseEntity<Auth> login(@RequestBody User user) {
		ServiceFacade.login(user);

		return new ResponseEntity<Auth>(authToken, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/user/login")
	public ResponseEntity<String> logout(@RequestHeader String sessionKey) {
		ServiceFacade.invalidateAuthToken(sessionKey);

		return new ResponseEntity<String>("This was a DELETE login API call", HttpStatus.OK);
	}
	
	@PostMapping("/api/user/reset")
	public ResponseEntity<String> reset(@RequestHeader String email) {
		ServiceFacade.requestReset(email);

		return new ResponseEntity<String>("This was a POST reset API call", HttpStatus.OK);
	}
	
	@PutMapping("/api/user")
	public ResponseEntity<String> updateUser(@RequestHeader String token, @RequestBody User user) {
		ServiceFacade.updateUser(token, user);

		return new ResponseEntity<String>("This was a PUT user API call", HttpStatus.OK);
	}
}
