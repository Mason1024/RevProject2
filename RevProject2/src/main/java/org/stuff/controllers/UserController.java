package org.stuff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class UserController {
	
	@Autowired
	UserService us;
	
	// Create
	
	public User createUser(@RequestBody User user) {
		
		return 
	}
	
	// Read
	
	// Update
	
	// Delete

}
