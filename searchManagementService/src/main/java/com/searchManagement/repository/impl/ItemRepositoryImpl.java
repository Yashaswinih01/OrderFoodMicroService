package com.searchManagement.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.searchManagement.model.Item;


@Repository
public class ItemRepositoryImpl {
	
	@Autowired
	private EntityManager em; 
	
	@SuppressWarnings("rawtypes")
	public List searchByitemsId(String restId) {
		TypedQuery<Item> query =
			       em.createQuery("select new com.searchManagement.model.Item(i.itemId,i.itemName,i.price,i.type) "
			       		+ "from Item as i ,Restnitem as ri where ri.restaurantId ='" + restId + "'"
		  		+ " and ri.itemId=i.itemId",Item.class);
			List<Item> results = query.getResultList();	
		return results;
	}
	
	@SuppressWarnings("rawtypes")
	public List searchByitemsType(String restId,String type) {
			
		TypedQuery<Item> query=em.createQuery(
		 "select distinct new com.searchManagement.model.Item(i.itemId,i.itemName,i.price,i.type) from Item i ,"
		 + "Restnitem ri where i.type='"+type+"' and ri.itemId=i.itemId and ri.restaurantId='"+restId+"'"
		  , Item.class);
		List<Item> itemList=query.getResultList();
		return itemList;
	}
	
	public List<Item> searchByitemsBudget(String restId,int price) {
		
		TypedQuery<Item> query=em.createQuery(
		 "select distinct new com.searchManagement.model.Item(i.itemId,i.itemName,i.price,i.type) from Item i ,"
		 + "Restnitem ri where i.price<='"+price+"' and ri.itemId=i.itemId and ri.restaurantId='"+restId+"'"
		  , Item.class);
		List<Item> itemList=query.getResultList();
		return itemList;
	}
	

}
