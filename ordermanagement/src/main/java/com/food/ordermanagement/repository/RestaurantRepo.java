package com.food.ordermanagement.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.ordermanagement.model.DeliveryBoy;
import com.food.ordermanagement.model.Restaurant;
import com.food.ordermanagement.model.User;

@Repository
public class RestaurantRepo implements JpaRepository<Restaurant, Long>{
	
	@Autowired
	EntityManager em;
	
	
	public DeliveryBoy getDeliveryDetails(String username,String restname,String item,int price)
	{
		//getting userdetails with the username
		 TypedQuery<User> qry =em.createQuery("from User s where s.name='"+username+"'",User.class);
         User fields =qry.getSingleResult();
         
         //inserting order details
         Query qury =em.createNativeQuery("insert into order_details(username,mail,itemname,restname,paid,phone) values('"+fields.getName()+"','"+fields.getEmail()+"','"+item+"','"+restname+"','"+price+"','"+fields.getPhone()+"')");
         qury.executeUpdate();
         
         //getting delivery boy details for the restaurant
         TypedQuery<DeliveryBoy> temp =em.createQuery("from DeliveryBoy d where for_rest='"+restname+"'",DeliveryBoy.class);
         DeliveryBoy boy =temp.getSingleResult();
         return boy;
	}
	
	
	
	
	@Override
	public Page<Restaurant> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Restaurant> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Restaurant> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Restaurant entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Restaurant> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Restaurant> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Restaurant> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Restaurant> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Restaurant> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Restaurant> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Restaurant> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Restaurant> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Restaurant> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Restaurant getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Restaurant> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Restaurant> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
