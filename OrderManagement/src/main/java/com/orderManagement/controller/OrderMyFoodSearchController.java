package com.orderManagement.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.orderManagement.model.Order;
import com.orderManagement.model.User;
import com.orderManagement.service.DeliveryDetailsService;
import com.orderManagement.service.OrderManagementService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



@RestController
@RequestMapping(
produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
public class OrderMyFoodSearchController {

		
	@Autowired
	OrderManagementService orderManagementService;
	
	@Autowired
	DeliveryDetailsService deliveryDetailsService;
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "Order";
	
    

    @ApiOperation(value="Order Food",
			notes="This method is used  to order the food and once the food is ordered message with order id details \r\n" + 
					"would be sent as response",
			response=String.class)
	@PostMapping("/orderFood")
	public ResponseEntity<?> orderFood(@RequestBody Order order) {
		String response=null;
		response=orderManagementService.orderFood(order);
		kafkaTemplate.send(TOPIC, response);
		return ResponseEntity.ok(response);
		
	}
	
	
    @ApiOperation(value="All Order Details",
			notes="This method lists all the orderDetails")
	@GetMapping("/allOrderDetails")
	public List<Order> getAllOrderDetails(){
		return deliveryDetailsService.getAllOrderDetails();
	}
	
	
	@ApiOperation(value="Order Details",
			notes="This method retrieves the order details like delivery time , restaurant details etc.. for particular order Id",
			response=String.class)
	@GetMapping("/orderDetails/{orderId}")
	public ResponseEntity<String> getOrderDetails(
			@ApiParam(value="Order Id to fetch the Order details")
			@PathVariable("orderId") int orderId){
		String response="";
		try {
			 response=deliveryDetailsService.getDeliveryDetails(orderId);
		}catch(Exception exp) {
			return new ResponseEntity<String>(exp.getMessage(),HttpStatus.NOT_FOUND);
		}
		kafkaTemplate.send(TOPIC, response);
		return ResponseEntity.ok(response);
		
	}
	
	@ApiOperation(value="Update Order Status",
			notes="Updates the Order Status based on the time",
			response=String.class)
	@GetMapping("/updateOrderStatus")
	public String updateOrderStatus() {
		String response="Order Status Updated";
		orderManagementService.updateOrderDetails("helo");
		return response;
	}
	
	@ApiOperation(value="User Details",
			notes="This method retrieves user details like name ,email id etc for the particular order id",
			response=User.class)
	@GetMapping("userDetails/{orderId}")
	public ResponseEntity<?> getUserDetails(@ApiParam(value="Order Id to fetch the User detail for that particular order")@PathVariable("orderId") int orderId){
		User user;
		try {
			 user=deliveryDetailsService.getUserDetails(orderId);
		}catch(Exception exp) {
			return new ResponseEntity<String>(exp.getMessage().toString(),HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(user);
		
	}
	
	/*@Value("${server.port}")
	String port;
	
	@Autowired
	RestTemplate rest;
	@GetMapping("/demo")
	public String demo() {
		String uri="http://user-management-service/demo";
				return rest.getForObject(uri, String.class);
		 
	}*/
	
}
