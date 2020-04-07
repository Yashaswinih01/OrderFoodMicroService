package com.orderManagement.Repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.model.Order;

@Repository
public interface OrderManagementRepository extends JpaRepository<Order, Integer> {
	
	


}
