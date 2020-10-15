package com.example.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {

	@PostMapping("/user/register")
	public ResponseEntity<String> register() {
		return new ResponseEntity<String>("This was a POST register API call", HttpStatus.OK);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<String> login() {
		return new ResponseEntity<String>("This was a POST login API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/user/login")
	public ResponseEntity<String> deleteUser() {
		return new ResponseEntity<String>("This was a DELETE login API call", HttpStatus.OK);
	}
	
	@PostMapping("/user/reset")
	public ResponseEntity<String> reset() {
		return new ResponseEntity<String>("This was a POST reset API call", HttpStatus.OK);
	}
	
	@PutMapping("/user")
	public ResponseEntity<String> updateUser() {
		return new ResponseEntity<String>("This was a PUT user API call", HttpStatus.OK);
	}
}
