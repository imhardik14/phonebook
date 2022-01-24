package com.hardik4u.phonebook.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hardik4u.phonebook.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{

	User findByUserName(String userName);
	
}
