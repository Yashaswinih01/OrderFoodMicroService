package com.food.ordermanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeliveryBoy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private long phone;
	
	private String forRest;
	
	public String getForRest() {
		return forRest;
	}
	public void setForRest(String forRest) {
		this.forRest = forRest;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public DeliveryBoy(long id, String name, long phone, String forRest) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.forRest = forRest;
	}
	public DeliveryBoy() {
		super();
	}
		

}
