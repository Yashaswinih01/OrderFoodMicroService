package com.searchManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.searchManagement.model.Restaurant;

import com.searchManagement.service.SearchManagementRestaurantService;

@RestController
public class SearchManagementController {
	
	@Autowired
	SearchManagementRestaurantService searchManagementService;


	
	@Autowired
    private KafkaTemplate<String, List<Restaurant>> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example2";

  
    /**
     * This methods lists all the restaurants details 
     * @return
     */
	@GetMapping("/allRestaurants")
	public List<Restaurant> searchAllRestaurants() {	
		List<Restaurant> data=searchManagementService.searchAllRestaurants();
		kafkaTemplate.send(TOPIC, data);
		return searchManagementService.searchAllRestaurants();
		
	}
	
	
    /**
     * This methods lists all the restaurants details based on distance provided in the request sent.
     * @param distance
     * @return
     */
	@GetMapping("/searchByDistance/{distance}")
	public List<Restaurant> searchByDistance(@PathVariable("distance") int distance) {		
		return searchManagementService.searchByDistance(distance); 
		
	}
	
	
	/**
	 * This methods lists all the restaurants details based on the rating provided in the request sent
	 * @param rating
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/searchByRating/{rating}")
	public List<Restaurant> searchByRating(@PathVariable("rating") double rating) {
		List<Restaurant> data =searchManagementService.searchByRating(rating);
		kafkaTemplate.send(TOPIC, data);
		return data;
		
	}
	
	/**
	 * 
	 * This methods lists all the restaurants details based on the Type provided in the request
	 * @param type
	 * @return
	 */
	@GetMapping("/searchByType/{type}")
	public List<Restaurant> searchByType(@PathVariable("type") String type) {
		return searchManagementService.searchByType(type);
		
	}
	
	/**
	 * 
	 * This methods lists all the restaurants details based on the Price provided in the request
	 * @param price
	 * @return
	 */
	@GetMapping("/searchByBudget/{price}")
	public List<Restaurant> searchByBudget(@PathVariable("price") int price) {
		return searchManagementService.searchByBudget(price);
		
	}
/*
	@GetMapping("{msg}")
	public String getMsg(@PathVariable("msg") String msg) {
		kafkaTemplate.send(TOPIC, msg);
		return msg;
	}*/

}
