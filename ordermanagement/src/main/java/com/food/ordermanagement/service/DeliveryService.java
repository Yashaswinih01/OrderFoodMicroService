package com.food.ordermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import com.food.ordermanagement.model.DeliveryBoy;
import com.food.ordermanagement.model.Restaurant;
import com.food.ordermanagement.repository.RestaurantRepo;

@Service
public class DeliveryService {
   
	@Autowired
	RestaurantRepo repo;
	
	
	//kafka listener
      @KafkaListener(topics = "kafke_producer_messaging", groupId = "group_json", containerFactory = "concurrentKafkaListenerContainerFactory")
	  public void consume(String brokerMessage) {
	   System.out.println("message: " + brokerMessage.toString());
		 }
	
	public DeliveryBoy getDelivery(String username,String restname,String item,int price) 
	{
	 return  repo.getDeliveryDetails(username, restname, item, price);	
	}
	
}
