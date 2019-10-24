package org.stuff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stuff.entities.User;
import org.stuff.services.UserService;

@Component
@RestController
public class UserController {
	


	
	@Autowired
	UserService us;
	
	// Create
	@RequestMapping(value="", method= RequestMethod.GET)
	public User createUser(@RequestBody User user) {
		
		return us.createUser(user);
	}
	
	// Read
	
	@RequestMapping(value= "/getUserById/{ID}", method= RequestMethod.GET)
	
	public User getUserById(@PathVariable int ID) {
		
		return us.getUserById(ID);
	}
	
	// Update
	
	@RequestMapping(value= "", method= RequestMethod.POST)
	public User updateUser(@RequestBody User user) {
		return us.updateUser(user);
	}
	
	// Delete
	
	@RequestMapping(value= "", method= RequestMethod.DELETE)
	public boolean deleteUser() {
		return false;
	}

}
