package org.stuff.services;

import java.util.Set;

import org.stuff.entities.User;
import org.stuff.entities.WebUser;

public interface UserService {

	// Create
	
	WebUser createUser(User user);
	
	
	// Read
	
	WebUser login(String username, String password);
	
	WebUser getUserById(int id);
	WebUser getUserByUsername(String username);
	Set<WebUser> getAllUsers();
	
	
	// Update
	
	WebUser updateUser(User user);
	
	
	// Delete
	
	boolean deleteUser(int id);
	
	
	
}
