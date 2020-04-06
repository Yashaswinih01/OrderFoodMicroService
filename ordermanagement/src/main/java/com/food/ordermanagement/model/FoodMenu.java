package com.food.ordermanagement.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


public class FoodMenu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String itemName;
	private String itemType;
	private double qtrPrice;
	private double halfPrice;
	private double fullPrice;
	
	
	@ManyToMany(mappedBy ="foodMenu",targetEntity=Restaurant.class)
	private Set<Restaurant> restaurant = new HashSet<>();


	public FoodMenu() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FoodMenu(Long id, String itemName, String itemType, double qtrPrice, double halfPrice, double fullPrice,
			Set<Restaurant> restaurant) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemType = itemType;
		this.qtrPrice = qtrPrice;
		this.halfPrice = halfPrice;
		this.fullPrice = fullPrice;
		this.restaurant = restaurant;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemType() {
		return itemType;
	}


	public void setItemType(String itemType) {
		this.itemType = itemType;
	}


	public double getQtrPrice() {
		return qtrPrice;
	}


	public void setQtrPrice(double qtrPrice) {
		this.qtrPrice = qtrPrice;
	}


	public double getHalfPrice() {
		return halfPrice;
	}


	public void setHalfPrice(double halfPrice) {
		this.halfPrice = halfPrice;
	}


	public double getFullPrice() {
		return fullPrice;
	}


	public void setFullPrice(double fullPrice) {
		this.fullPrice = fullPrice;
	}


	public Set<Restaurant> getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Set<Restaurant> restaurant) {
		this.restaurant = restaurant;
	}



}
