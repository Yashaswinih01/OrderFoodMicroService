package com.searchManagement.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.searchManagement.model.Restaurant;


@Repository
public class RestaurantRepositoryImpl {

	@Autowired
	private EntityManager em; 
	
	
	public List<Restaurant> findAllRestaurants() {
		TypedQuery<Restaurant> query =
		      em.createQuery("from Restaurant", Restaurant.class);
		List<Restaurant> results = query.getResultList();
		 return results;	
	}
	
	public List<Restaurant> findByDistance(int distance) {
		TypedQuery<Restaurant> query =
		      em.createQuery("from Restaurant r where r.distanceInKm <='"+distance+"'", Restaurant.class);
		List<Restaurant> results = query.getResultList();
		 return results;	
	}
	
	
	public List<Restaurant> findByRating(double rating) {
		TypedQuery<Restaurant> query =
			      em.createQuery("from Restaurant r where r.rating >='"+rating+"'", Restaurant.class);
			List<Restaurant> results = query.getResultList();
			 return results;	
	}
	
	public List<Restaurant> findByRestaurantId(String itemId) {
		TypedQuery<Restaurant> query =
			       em.createQuery("select new com.searchManagement.model.Restaurant(r.restaurantId,r.distanceInKm,"
			       		+ "r.rating,r.restaurantName) "
			       		+ "from Restaurant as r ,Restnitem as ri where ri.itemId ='" + itemId + "'"
		  		+ " and ri.restaurantId=r.restaurantId",Restaurant.class);
			List<Restaurant> results = query.getResultList();
			
		return results;
	}
	
	public List<Restaurant> findByType(String type){
		TypedQuery<Restaurant> query=em.createQuery("select distinct new com.searchManagement.model.Restaurant(r.restaurantId,r.distanceInKm,"
	       		+ "r.rating,r.restaurantName) "
	       		+ "from Restaurant as r ,Restnitem as ri where ri.restaurantId=r.restaurantId and "
	       		+ "ri.itemId in ( select i.itemId from Item as i where i.type='"+type+"')",Restaurant.class);
		List<Restaurant> results = query.getResultList();
		return results;
		
	}
	
	public List<Restaurant> findByBudget(int price){
		TypedQuery<Restaurant> query=em.createQuery("select distinct new com.searchManagement.model.Restaurant(r.restaurantId,r.distanceInKm,"
	       		+ "r.rating,r.restaurantName) "
	       		+ "from Restaurant as r ,Restnitem as ri where ri.restaurantId=r.restaurantId and "
	       		+ "ri.itemId in ( select i.itemId from Item as i where i.price<='"+price+"')",Restaurant.class);
		List<Restaurant> results = query.getResultList();
		return results;
		
	}
}
