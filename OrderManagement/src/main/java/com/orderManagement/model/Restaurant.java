package com.orderManagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Restaurant {
	
	
	@Id
	private String restaurantId;
	private String restaurantName;
	private int distanceInKm;
	private double rating;
	@Column(name="location")
	private String address;
	@Column(name="phone_number")
	private long phoneNumber;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Transient
	@JsonInclude(Include.NON_NULL)
	private List<Item> itemList;
	
	
	public Restaurant() {}
	
	public Restaurant(String restaurantId, int distanceInKm, double rating, String restaurantName) {
		super();
		this.restaurantId = restaurantId;
		this.distanceInKm = distanceInKm;
		this.rating = rating;
		this.restaurantName = restaurantName;
	}
	
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public int getDistanceInKm() {
		return distanceInKm;
	}
	public void setDistanceInKm(int distanceInKm) {
		this.distanceInKm = distanceInKm;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

}
