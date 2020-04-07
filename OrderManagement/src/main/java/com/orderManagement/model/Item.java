package com.orderManagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity

public class Item {
	
	@Id
	private String itemId;
	private String itemName;
	private int price;
	private String type;
	@Transient
	@JsonInclude(Include.NON_NULL)
	private List<Restaurant> restList;
	
	public List<Restaurant> getRestList() {
		return restList;
	}
	public void setRestList(List<Restaurant> restList) {
		this.restList = restList;
	}
	public Item() {
		
	}
	public Item(String itemId,String itemName,int price,String type) {
		this.itemId=itemId;
		this.itemName=itemName;
		this.price=price;
		this.type=type;	
		
	}
	
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
