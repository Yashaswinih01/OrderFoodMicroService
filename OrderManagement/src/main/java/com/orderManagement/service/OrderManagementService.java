package com.orderManagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderManagement.Repository.ItemRepository;
import com.orderManagement.Repository.OrderItemRepository;
import com.orderManagement.Repository.OrderManagementRepository;
import com.orderManagement.model.Item;
import com.orderManagement.model.Order;
import com.orderManagement.model.OrderItem;

@Service
public class OrderManagementService {
	
	@Autowired
	OrderManagementRepository orderManagementRepository;
	
	@Autowired
	ItemRepository itemRepositoryImpl;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	static List<String> deliveryBoysList=Arrays.asList("Deepak","Ganesh","Ravi","Mahesh","Harish","Prathap");
	
	
	/**
	 * This service method is used to place the order using ordermanagementRepository.
	 * Response message would include order ID details.
	 * @param order
	 * @return
	 */
	@Modifying
	@Transactional
	public String orderFood(Order order) {
		setOrderAndDeliveryTime(order);
		Random random=new Random();
		order.setDeliveryBoyName(deliveryBoysList.get(random.nextInt(6)));
		Order persistedOrder=orderManagementRepository.save(order);
		String  result=calculateTotalPrice(persistedOrder);
		return "\n Your order is successfully placed !!!! \n Your order Id is : "+String.valueOf(persistedOrder.getOrderId()) +""
				+ result;
	}
	
	
	/**
	 * This method calculates the total price for the order food including GST fare.
	 * @param order
	 * @return
	 */
	private String calculateTotalPrice(Order order) {
		double totalPrice=0.0;
		List<String> itemIdList=order.getItemIdList();
		List<Item> itemsList=new ArrayList<>();
		String priceDetials="";
		int orderId=order.getOrderId();
		for(String item:itemIdList) {
			Optional<Item> item2=itemRepositoryImpl.findById(item);
			if(item2.isPresent()){
				Item fetchedItem=item2.get();
				itemsList.add(fetchedItem);
				priceDetials += fetchedItem.getItemName() + " : " + fetchedItem.getPrice() + " Rs" + "\n";
				totalPrice+= item2.get().getPrice();
				saveItemnOrderDetails(fetchedItem.getItemId(),orderId);
			}	
		}
		double gst=totalPrice*0.05;
		totalPrice+=gst;
		String response ="\n\nPrice Details:\n" + priceDetials
							+ "GST= " + gst + " Rs"
		              + "\n \nYour total order cost Incl GST = " + totalPrice +" Rs" ;
		              
	
		return response;
	}

	/**
	 * This method persists the Item and order details for particular order id
	 * @param itemId
	 * @param orderId
	 */
	public void saveItemnOrderDetails(String itemId,int orderId) {
		OrderItem orderItem=new OrderItem();
		orderItem.setItemId(itemId);
		orderItem.setOrderId(orderId);
		orderItemRepository.save(orderItem);
	}
	
	
	/**
	 * This method sets the delivery time to 30 minutes from the order time
	 * @param order
	 */
	public void setOrderAndDeliveryTime(Order order) {
		final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
		Date currentTime=new Date();
		long t= currentTime.getTime();
		Date deliveryTime=new Date(t + (30*ONE_MINUTE_IN_MILLIS));
		order.setDeliveryTime(deliveryTime);
		order.setOrderTime(currentTime);	
	}

}
