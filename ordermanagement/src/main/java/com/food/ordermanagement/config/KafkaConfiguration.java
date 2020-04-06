package com.food.ordermanagement.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;








@EnableKafka
@Configuration
public class KafkaConfiguration {

//	@Bean
//	public ConsumerFactory<String,String> consumerFactory()
//	{
//		Map<String, Object> config =new HashMap<>();
//		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
//		config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_id");
//		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
//		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
//		return new DefaultKafkaConsumerFactory<String, String>(config);
//		
//	}
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String,String> KafkaListenerContainerFactory()
//	{
//		ConcurrentKafkaListenerContainerFactory<String,String> factory =new ConcurrentKafkaListenerContainerFactory();
//		factory.setConsumerFactory(consumerFactory());
//		return factory;
//	}
//	
//	@Bean
//	public ConsumerFactory<String, DeliveryBoy> userConsumerFactory()
//	{
//		Map<String, Object> config =new HashMap<>();
//		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
//		config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_json");
//		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
//		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
//	    return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),new JsonDeserializer<>(DeliveryBoy.class));	
//	}
//	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String,DeliveryBoy> userKafkaListenerContainerFactory()
//	{
//		ConcurrentKafkaListenerContainerFactory<String,DeliveryBoy> factory =new ConcurrentKafkaListenerContainerFactory();
//		factory.setConsumerFactory(userConsumerFactory());
//		return factory;
//	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(config));
		return factory;
	}
}
