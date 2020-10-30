package com.example.api;


import com.example.model.User;
import com.example.service.ServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserAccountController {

	@PostMapping("/api/user/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		ServiceFacade.register(user);

		return new ResponseEntity<String>("This was a POST register API call", HttpStatus.OK);
	}
	
	@PostMapping("/api/user/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		ServiceFacade.login(user);

		return new ResponseEntity<String>("This was a POST login API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/user/login")
	public ResponseEntity<String> logout(@RequestHeader String authToken) {
		ServiceFacade.invalidateAuthToken(authToken);

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
