package org.stuff.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.stuff.entities.User;


@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes=org.stuff.app.RevProject2Application.class)
public class UserServiceTest {


	@Autowired
	UserService us;
	
	
	// saving an instance of user for testing
	static User testUser;
	
	

	
	@Test
	@Order(1)
	@Commit
	void setup() {
		testUser = new User();
		testUser.setUsername("TestUser");
		testUser.setPassword("Pa$$word");
		testUser.setEmail("TestUser@test.com");
		testUser.setPhoneNumber("1234567890");
		testUser = us.createUser(testUser);
	}
	
	// Create
	
	@Test
	@Order(2)
	void createUser() {
		
		assertTrue(testUser != null);
	}
	
	

//	// Read
	
	@Test
	@Order(3)
	void getUserById() {
		User result = us.getUserById(testUser.getId());
		assertTrue(result != null);
		assertTrue(result.getId() == testUser.getId());
	}
	
	
	@Test
	@Order(3)
	void getUserByUsername(String username) {
		User result = us.getUserByUsername(testUser.getUsername());
		assertTrue(result != null);
		assertTrue(result.getUsername().equals(testUser.getUsername()));
	}
	
	@Test
	@Order(3)
	void getAllUsers() {
		User tempUser = new User();
		tempUser.setUsername("secondUser");
		tempUser.setPassword("Pa$$word1");
		tempUser.setEmail("TestUser@test.com");
		tempUser.setPhoneNumber("1234567890");
		tempUser = us.createUser(tempUser);
		
		Set<User> result = us.getAllUsers();

		assertTrue(result != null);
		assertTrue(result.contains(testUser));
		assertTrue(result.contains(tempUser));
		
	}

	
	
	// Update
	
//	User updateUser(User user);
//	
//	
//	// Delete
//	
//	boolean deleteUser(User user);
//	
//	
}
