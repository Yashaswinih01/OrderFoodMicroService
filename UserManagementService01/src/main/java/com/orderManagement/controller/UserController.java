package com.orderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.model.User;
import com.orderManagement.service.UserDetailsService;

@RestController
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	/**
	 * This method provides the user details like name,phone number etc for the particular userId
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/userDetails/{userId}")
	public ResponseEntity getUserDetailsById(@PathVariable("userId") String userId) {
		try {
		return ResponseEntity.ok(userDetailsService.getUserDetails(userId));
		}catch(Exception exp) {
			return new  ResponseEntity(exp.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * This method updates the user phone number for the particular user Id sent in the request
	 * 
	 * @param userId
	 * @param user
	 * @return
	 */
	@PutMapping("/updatePhoneNumber/{id}")
	public ResponseEntity<String> updatePhoneNumber(@PathVariable("id") String userId,
			@RequestBody User user) {
		try {
			return ResponseEntity.ok(userDetailsService.updatePhoneNumber(user,userId));
			}catch(Exception exp) {
				return new  ResponseEntity(exp.getMessage(),HttpStatus.NOT_FOUND);
			}	
	}

	
	/**
	 * This method calls the service method to save user details  in the repository.
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/addUser")
	public String addUser(@RequestBody User user) {
		return userDetailsService.addUser(user);
	}
	
}
