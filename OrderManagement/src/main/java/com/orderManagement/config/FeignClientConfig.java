package com.orderManagement.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orderManagement.model.User;

@Configuration
@FeignClient(name="user-management-service")
public interface FeignClientConfig {

	@GetMapping("/userDetails/{userId}")
	public ResponseEntity<User> getUserDetailsById(@PathVariable("userId") String userId);

}
