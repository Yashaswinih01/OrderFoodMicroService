package com.orderManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.model.Order;
import com.orderManagement.model.User;
import com.orderManagement.service.DeliveryDetailsService;
import com.orderManagement.service.OrderManagementService;


@RestController
public class OrderMyFoodSearchController {
	
	@Autowired
	OrderManagementService orderManagementService;
	
	@Autowired
	DeliveryDetailsService deliveryDetailsService;
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";
	
    
    /**
     * This method is used  to order the food and once the food is ordered message with order id details 
     * would be sent as response.
     * @param order
     * @return
     */
	@PostMapping("/orderFood")
	public ResponseEntity orderFood(@RequestBody Order order) {
		String response=null;
		response=orderManagementService.orderFood(order);
		return ResponseEntity.ok(response);
		
	}
	
	
	/**
	 * This method lists all the orderDetails
	 * @return
	 */
	@GetMapping("/allOrderDetails")
	public List<Order> getAllOrderDetails(){
		return deliveryDetailsService.getAllOrderDetails();
	}
	
	
	/**
	 * This method retrieves the order details like delivery time , restaurant details etc.. for particular order Id.
	 * @param orderId
	 * @return
	 */
	@GetMapping("/orderDetails/{orderId}")
	public ResponseEntity<String> getOrderDetails(@PathVariable("orderId") int orderId){
		String response="";
		try {
			 response=deliveryDetailsService.getDeliveryDetails(orderId);
		}catch(Exception exp) {
			return new ResponseEntity<String>(exp.getMessage(),HttpStatus.NOT_FOUND);
		}
		//kafkaTemplate.send(TOPIC, response);
		return ResponseEntity.ok(response);
		
	}
	
	
	/**
	 * This method retrieves user details like name ,email id etc for the particular order id.
	 * @param orderId
	 * @return
	 */
	@GetMapping("userDetails/{orderId}")
	public ResponseEntity getUserDetails(@PathVariable("orderId") int orderId){
		User user;
		try {
			 user=deliveryDetailsService.getUserDetails(orderId);
		}catch(Exception exp) {
			return new ResponseEntity<String>(exp.getMessage().toString(),HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(user);
		
	}
}
