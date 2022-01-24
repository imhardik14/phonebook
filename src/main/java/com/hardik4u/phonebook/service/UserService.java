package com.hardik4u.phonebook.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hardik4u.phonebook.model.Phone;
import com.hardik4u.phonebook.model.User;
import com.hardik4u.phonebook.repository.PhoneRepository;
import com.hardik4u.phonebook.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PhoneRepository phoneRepository;

	public User getUser(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser( User user,UUID userId){
		Optional<User> updateUserOpt = userRepository.findById(userId);
		User updateUserObj = updateUserOpt.get();
    	if(updateUserOpt.isPresent()) {
    		updateUserObj.setEmail(user.getEmail());
    		updateUserObj.setPassword(user.getPassword());
    		updateUserObj.setType(user.getType());
    		updateUserObj.setPhones(user.getPhones());
        	
    	}
    	
		return userRepository.save(updateUserObj);
	}
	
	public void deleteUser(UUID userId) {
		userRepository.deleteById(userId);
	}
	
	public List<Phone> getPhonesByUserId(UUID userId){
		
		User user = userRepository.findById(userId).get();
		return phoneRepository.findByUser(user) ;
	}
	
}
