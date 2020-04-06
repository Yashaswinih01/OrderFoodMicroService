package com.food.ordermanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String username;
	
	private String mail;
	
	private String restname;
	
	public String getRestname() {
		return restname;
	}
	public void setRestname(String restname) {
		this.restname = restname;
	}

	private String itemname;
	private long phone;
	
	private long paid;
	
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getPaid() {
		return paid;
	}
	public void setPaid(long paid) {
		this.paid = paid;
	}
	
	
	public OrderDetails(long id, String username, String mail, String restname, String itemname, long phone,
			long paid) {
		super();
		this.id = id;
		this.username = username;
		this.mail = mail;
		this.restname = restname;
		this.itemname = itemname;
		this.phone = phone;
		this.paid = paid;
	}
	public OrderDetails() {
		super();
	}
	
}
