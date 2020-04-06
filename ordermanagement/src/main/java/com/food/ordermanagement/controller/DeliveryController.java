package com.food.ordermanagement.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordermanagement.model.DeliveryBoy;
import com.food.ordermanagement.service.DeliveryService;


@RestController
public class DeliveryController {
	
	
	
	

	@Autowired
	DeliveryService ser;
	
	@GetMapping("/dboy/username/{username}/restname/{restname}/item/{item}/price/{price}")
	@Transactional
	@Modifying
	public DeliveryBoy getDeliveryBoyDetails(@PathVariable String username,@PathVariable
			String restname,
			@PathVariable String item,
			@PathVariable int price)
	{
		return ser.getDelivery(username, restname, item, price);
	}
}
