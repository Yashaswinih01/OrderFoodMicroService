package com.orderManagement.model;

import java.io.Serializable;

import javax.persistence.Id;

public class OrderItemId implements Serializable{
	
	private int orderId;

	public OrderItemId() {
		super();
	}

	public OrderItemId(int orderId, String itemId) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
	}

	private String itemId;

}
