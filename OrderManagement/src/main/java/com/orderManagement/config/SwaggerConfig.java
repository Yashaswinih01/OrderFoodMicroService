package com.orderManagement.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	 Contact contact = new Contact(
	         "Yashaswini",
	         null, 
	         null
	 );
	 
	 List<VendorExtension> vendorExtensions = new ArrayList<>();
	 
	 ApiInfo apiInfo = new ApiInfo(
			 "Oder Management API", 
			 "This API handles all order detail in My Food App", "1.0",
			 "Terms Of Service Url", contact, 
			 "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				//.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.orderManagement.controller"))
				.build()
				.apiInfo(apiInfo);
			
				
	}
	
	
}
