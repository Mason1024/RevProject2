package org.stuff.services;

import java.util.Set;

import org.stuff.entities.User;

public interface UserService {

	// Create
	
	User createUser(User user);
	
	
	// Read
	
	User login(String username, String password);
	
	User getUserById(int id);
	User getUserByUsername(String username);
	Set<User> getAllUsers();
	
	
	// Update
	
	User updateUser(User user);
	
	
	// Delete
	
	boolean deleteUser(User user);
	
	
	
}
