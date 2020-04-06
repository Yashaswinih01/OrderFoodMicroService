package com.food.ordermanagement.controller;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.netflix.ribbon.proxy.annotation.Content;

//as spring project used springjunit4 class runner
@RunWith(SpringJUnit4ClassRunner.class)
public class DeliveryControllerTest {
	
	
	
	private MockMvc mockMvc;

	//helps mocking this resource
	@InjectMocks
	private DeliveryController deliveryController;
	
	@Before
	private void setUp() throws Exception{
		mockMvc =MockMvcBuilders.standaloneSetup(deliveryController)
				 .build();
	}
	
	@Test
	private void testDeliveryBoyDetails() throws Exception {
		
		mockMvc.perform(get("/dboy/username/{username}/restname/{restname}/item/{item}/price/{price}"))
		.
	}
	
	
	

	
}
