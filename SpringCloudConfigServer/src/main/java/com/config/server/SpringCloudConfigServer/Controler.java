package com.config.server.SpringCloudConfigServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controler {

	
	@Value("${my.msg}")
	String msg;
	
	@GetMapping("/get")
	public String get() {
		return msg;
	}
}
