package com.hardik4u.phonebook.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hardik4u.phonebook.model.Phone;
import com.hardik4u.phonebook.model.User;

public interface PhoneRepository extends JpaRepository<Phone, UUID>{

	List<Phone> findByUser(User user);
}
