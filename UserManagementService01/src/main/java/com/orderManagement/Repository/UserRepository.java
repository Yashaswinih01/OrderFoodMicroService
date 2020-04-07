package com.orderManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
