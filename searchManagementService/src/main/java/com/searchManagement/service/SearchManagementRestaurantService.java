package com.searchManagement.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchManagement.model.Item;
import com.searchManagement.model.Restaurant;
import com.searchManagement.repository.impl.ItemRepositoryImpl;
import com.searchManagement.repository.impl.RestaurantRepositoryImpl;

@Service
public class SearchManagementRestaurantService {
	
	@Autowired
	RestaurantRepositoryImpl searchManagementRepositoryImpl;
	
	@Autowired
	ItemRepositoryImpl itemRepositoryImpl;
	
	
	/**
	 * This method fetches all the restaurants details from search management repository 
	 * based on the distance provided in the request. 
	 * 
	 * 
	 * @param distance
	 * @return
	 */
	public List<Restaurant> searchByDistance(int distance) {
		List<Restaurant> list;
		list= searchManagementRepositoryImpl.findByDistance(distance);
		populateItemList(list);
		return list;
	}
	
	
	/**
	 * This method fetches all the restaurants details from search management repository 
	 * 
	 * @return
	 */
	public List<Restaurant> searchAllRestaurants() {
		List<Restaurant> list;
		list= searchManagementRepositoryImpl.findAllRestaurants();
		populateItemList(list);
		return list;
	}

	/**
	 * This method fetches all the restaurants details from search management repository 
	 * based on the Rating provided in the request. 
	 * 
	 * @param rating
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List searchByRating(double rating) {
		List<Restaurant> list;
		list= searchManagementRepositoryImpl.findByRating(rating);
		populateItemList(list);
		return list;	
	}
    
	
	/**
	 * This method populated the item details into the Item table using ItemRepository
	 * @param list
	 */
	private void populateItemList(List<Restaurant> list) {
		for(Restaurant rest:list) {
		  @SuppressWarnings("unchecked")
		List<Item> itemList = itemRepositoryImpl.searchByitemsId(rest.getRestaurantId());
		  rest.setItemList(itemList);
		}
	}

    /**
     * This method fetches all the restaurants details from search management repository 
	 * based on the Type provided in the request. 
     * @param type
     * @return
     */
	public List<Restaurant> searchByType(String type) {
		List<Restaurant> list =searchManagementRepositoryImpl.findByType(type);
		
		for(Restaurant rest:list) {
			  @SuppressWarnings("unchecked")
			List<Item> itemList = itemRepositoryImpl.searchByitemsType(rest.getRestaurantId(),type);
			  rest.setItemList(itemList);
			}
		
		return list;
	}
	
	
	/**
	 * This method fetches all the restaurants details from search management repository 
	 * based on the Price provided in the request. 
	 * @param price
	 * @return
	 */
	public List<Restaurant> searchByBudget(int price) {
		List<Restaurant> list =searchManagementRepositoryImpl.findByBudget(price);
		
		for(Restaurant rest:list) {
			  List<Item> itemList = itemRepositoryImpl.searchByitemsBudget(rest.getRestaurantId(),price);
			  rest.setItemList(itemList);
			}
		
		return list;
	}
	
}
