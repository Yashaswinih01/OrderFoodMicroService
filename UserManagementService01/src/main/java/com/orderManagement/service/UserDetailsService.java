package com.orderManagement.service;

import java.security.InvalidParameterException;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManagement.Repository.UserRepository;
import com.orderManagement.model.Order;
import com.orderManagement.model.User;

@Service
public class UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * This method retrieves the user details like name,phone number etc for the particular userId from the user Repository
	 * 
	 * @param userId
	 * @return
	 */
	
	public User getUserDetails(String userId) {
		Optional<User> user= userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new InvalidParameterException("User Id is not valid !! . Please enter valid User Id ");
		}	
		
	}
	
	
	/**
	 * This method updates the user phone number for the particular user Id using user Repository
	 * 
	 * @param user
	 * @param userId
	 * @return
	 */
	public String updatePhoneNumber(User user,String userId) {
		String response;
		Optional<User> fetchUser= userRepository.findById(userId);
		if(fetchUser.isPresent()) {
			User existingUser= fetchUser.get();
			existingUser.setPhoneNumber(user.getPhoneNumber());
			userRepository.save(existingUser);
	       response =" Phone Number updated successfully !!"	;
		}else {
			throw new InvalidParameterException("User Id is not valid !! . Please enter valid User Id ");
		}	
		return response;
	}

	
	/**
	 * This method saves user object into the table using userRepository
	 * and return success message once done
	 * 
	 * @param user
	 * @return
	 */
	public String addUser(User user) {
		String response="User added successfully !! ";
		userRepository.save(user);
		return response;
	}

}
