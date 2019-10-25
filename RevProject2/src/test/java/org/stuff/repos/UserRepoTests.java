package org.stuff.repos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.stuff.entities.User;
import org.stuff.repos.UserRepo;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes=org.stuff.app.RevProject2Application.class)
public class UserRepoTests {

	@Autowired
	UserRepo ur;
	
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
		testUser = ur.save(testUser);
	}
	
	@Test
	@Order(2)
	void findByUsername() {
		User found = ur.findByUsername(testUser.getUsername());
		assertTrue(found.getUsername().equals(testUser.getUsername()));
		assertTrue(found.getPassword().equals(testUser.getPassword()));
	}
	
	@Test
	@Order(2)
	void findByEmail() {
		User found = ur.findByEmail(testUser.getEmail());
		assertTrue(found.getUsername().equals(testUser.getUsername()));
		assertTrue(found.getPassword().equals(testUser.getPassword()));
	}
	
	@Test
	@Order(3)
	@Commit
	void teardown() {
		ur.delete(testUser);
	}
	
}
