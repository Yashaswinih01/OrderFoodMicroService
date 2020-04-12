package com.orderManagement.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.orderManagement.Repository.OrderManagementRepository;
import com.orderManagement.Repository.RestautantRepository;
import com.orderManagement.config.FeignClientConfig;
import com.orderManagement.model.Order;
import com.orderManagement.model.Restaurant;
import com.orderManagement.model.User;


@Service
public class DeliveryDetailsService {
	
	@Autowired
	OrderManagementRepository orderManagementRepository;
	
	@Autowired
	RestautantRepository restautantRepository;

	@Autowired
	FeignClientConfig feignClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	/**
	 * This service method retrieves the order details from ordermanagementReposioty for the particular order Id.
	 * It throws exception is invalid order id is provided
	 * @param orderId
	 * @return
	 */
	public String getDeliveryDetails(int orderId) {
		String response="";
		Optional<Order> optionalOrder=orderManagementRepository.findById(orderId);
		if(optionalOrder.isPresent()) {
			Order order=optionalOrder.get();
			Restaurant rest=restautantRepository.findById(order.getRestId()).get();
			response = "Your Order would be delivered by "+ order.getDeliveryBoyName() + " on " + order.getDeliveryTime() 
			            + "\nFrom the Restaurant : \n" +rest.getRestaurantName() + ", "
			            + rest.getAddress() +", " + rest.getPhoneNumber();
		}else {
			throw new InvalidParameterException("Order Id is not valid !! Please enter valid Order ID ");
		}
		
		
		return response;
	}
	
	
	/**
	 * This service method retrieves all the order details from ordermanagementReposioty .
	 * @return
	 */
	public List<Order> getAllOrderDetails() {
		
		List<Order> allOrder=orderManagementRepository.findAll();
				
		
		return allOrder;
	}
	
	
	
	
	/**
	 * This service method retrieves the user details from user management services which runs in a different port.
	 * User details for particular order id is retrieved. If invalid order id is sent then exception would be thrown.
	 * 
	 * Hystrix is also implemented to provide fall back method.
	 * 
	 * @param orderId
	 * @return
	 */
	@HystrixCommand(fallbackMethod="userDetailsFallBack",
			 commandProperties = {
				      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
				      @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6"),
				      @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
				      @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
				   })
	public User getUserDetails(int orderId) {
		User user=null;
		Optional<Order> order=orderManagementRepository.findById(orderId);
		if(order.isPresent()) {
			String userId=order.get().getUserId();
			/*String url="http://user-management-service/userDetails/" + userId;
			user=restTemplate.getForObject(url, User.class);*/
			
	     user= feignClient.getUserDetailsById(userId).getBody();
			
			//System.out.println("in feign");
		}else {
			throw new InvalidParameterException("Order Id is not valid !! Please enter valid Order ID ");
			
		}
		
		return user;
	}

	/**
	 * This is the fall back method for getUserDetails method . This method would be called if any issue in seen while 
	 * executing getUserDetails method.
	 * 
	 * As part of fall back response it would return an empty user.
	 * @param orderId
	 * @return
	 */
	public User userDetailsFallBack(int orderId) {
		System.out.println("in fall back");
		return new User();
	}
	
	
}
