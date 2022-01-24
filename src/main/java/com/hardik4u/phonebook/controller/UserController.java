package com.hardik4u.phonebook.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardik4u.phonebook.model.Phone;
import com.hardik4u.phonebook.model.User;
import com.hardik4u.phonebook.service.UserService;


@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService service;
	
		
	@GetMapping(value = "/{userName}")
	public ResponseEntity<User> getUser(@PathVariable String userName) {
				
		return new ResponseEntity<>(service.getUser(userName), HttpStatus.OK);
	}
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<User>> getAllUsers() {
				
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
	
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		
		return new ResponseEntity<>(service.saveUser(user), HttpStatus.CREATED);
	}
	
	// Update operation
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("userId") UUID userId) {
   	
		return  new ResponseEntity<>(service.updateUser(user, userId), HttpStatus.OK);
	}
	
    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteByUserId(@PathVariable("userId") UUID userId)
    {
    	service.deleteUser(userId);
  
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping(value = "/phones/{userId}")
    public List<Phone> getPhonesByUserId(@PathVariable("userId") UUID userId){
    	return service.getPhonesByUserId(userId);
    }
}
