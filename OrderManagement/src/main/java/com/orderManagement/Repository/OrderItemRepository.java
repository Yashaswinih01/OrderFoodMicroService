package com.orderManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.model.OrderItem;
import com.orderManagement.model.OrderItemId;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
	
	

}
