package com.food.ordermanagement.config;



import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;




@Service
public class KafkaConsumer {

	@KafkaListener(topics = "order_service", groupId = "group_json", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void consume(String message)
	{
		System.out.println(message);
	}
	
}
