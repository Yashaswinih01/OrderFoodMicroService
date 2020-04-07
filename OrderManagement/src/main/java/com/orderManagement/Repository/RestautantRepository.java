package com.orderManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.model.Restaurant;

@Repository
public interface RestautantRepository extends JpaRepository<Restaurant, String> {

}
