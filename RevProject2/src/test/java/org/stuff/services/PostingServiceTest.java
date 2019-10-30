package org.stuff.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

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
import org.stuff.entities.Posting;
import org.stuff.entities.User;
import org.stuff.entities.WebPosting;
import org.stuff.entities.WebUser;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes=org.stuff.app.RevProject2Application.class)
public class PostingServiceTest {

	@Autowired
	UserService us;
	@Autowired
	PostingService ps;
	
	static WebUser testUser1;
	static WebUser testUser2;
	
	static WebPosting testPosting1;
	static WebPosting testPosting2;
	static WebPosting testPosting3;
	static WebPosting testPosting4;
	static WebPosting testPosting5;
	static WebPosting testPosting6;
	
	@Test
	@Order(1)
	@Commit
	void setup() {
		testUser1 = us.createUser(new User(0, "PSTestUser1", "TestUser", null, null));
		testUser2 = us.createUser(new User(0, "PSTestUser2", "TestUser", null, null));
		
		testPosting1 = ps.createPosting(new Posting(0, new User(testUser1), "PSTestItem1", "desc", "Table", "TestLocation", Long.MAX_VALUE-6, 1, null));
		testPosting2 = ps.createPosting(new Posting(0, new User(testUser1), "PSTestItem2", "desc", "Chair", "TestLocation", Long.MAX_VALUE-5, 0, null));
		testPosting3 = ps.createPosting(new Posting(0, new User(testUser1), "PSTestItem3", "desc", "Chair", "TestLocation", Long.MAX_VALUE-4, 2, null));
		testPosting4 = ps.createPosting(new Posting(0, new User(testUser2), "PSTestItem4", "desc", "Table", "TestLocation", Long.MAX_VALUE-3, 0, null));
		testPosting5 = ps.createPosting(new Posting(0, new User(testUser2), "PSTestItem5", "desc", "Chair", "TestLocation", Long.MAX_VALUE-2, 3, null));
		testPosting6 = ps.createPosting(new Posting(0, new User(testUser2), "PSTestItem6", "desc", "Table", "TestLocation", Long.MAX_VALUE-1, 0, null));
	}
	
	@Test
	@Order(2)
	void getPostingById() {
		WebPosting found = ps.getPostingById(testPosting1.getId());
		assertTrue(found!=null);
		assertTrue(found.equals(testPosting1));
	}
	@Test
	@Order(2)
	void getAllPostingsByUser() {
		Set<WebPosting> items = ps.getAllPostingsByUser(new User(testUser1));
		assertTrue(items.contains(testPosting1));
		assertTrue(items.contains(testPosting2));
		assertTrue(items.contains(testPosting3));
	}
	@Test
	@Order(2)
	void getAllPostingsByCategory() {
		Set<WebPosting> items = ps.getAllPostingsByCategory("Table");
		assertTrue(items.contains(testPosting1));
		assertTrue(items.contains(testPosting4));
		assertTrue(items.contains(testPosting6));
	}
	@Test
	@Order(2)
	void getAllPostingByEndingSoonest() {
		List<WebPosting> items = ps.getAllPostingByEndingSoonest();
		assertTrue(items.get(0).equals(testPosting1));
		assertTrue(items.get(1).equals(testPosting3));
		assertTrue(items.get(2).equals(testPosting5));
	}
	@Test
	@Order(2)
	void getNewestByRange() {
		List<WebPosting> items = ps.getNewestByRange(2, 4);
		assertTrue(items.get(0).equals(testPosting4), "Item 2");
		assertTrue(items.get(1).equals(testPosting3), "Item 3");
	}
	@Test
	@Order(2)
	void getAllPostings() {
		List<WebPosting> items = ps.getAllPostings();
		assertTrue(items.contains(testPosting1));
		assertTrue(items.contains(testPosting2));
		assertTrue(items.contains(testPosting3));
		assertTrue(items.contains(testPosting4));
		assertTrue(items.contains(testPosting5));
		assertTrue(items.contains(testPosting6));
	}
	@Test
	@Order(3)
	void updatePosting() {
		String oldLoc = testPosting1.getLocation();
		String newLoc = "NewLocation";
		WebPosting temp = ps.updatePosting(new Posting(0, new User(testUser1), "PSTestItem1", "desc", "Table", newLoc, Long.MAX_VALUE-6, 1, null));
		
		assertTrue(temp!=null);
		assertTrue(temp.getLocation().equals(newLoc));
		
		testPosting1.setLocation(oldLoc);
	}
	
	@Test
	@Order(4)
	@Commit
	void teardown() {
		ps.deletePosting(testPosting1.getId());
		ps.deletePosting(testPosting2.getId());
		ps.deletePosting(testPosting3.getId());
		ps.deletePosting(testPosting4.getId());
		ps.deletePosting(testPosting5.getId());
		ps.deletePosting(testPosting6.getId());
		
		us.deleteUser(testUser1.getId());
		us.deleteUser(testUser2.getId());
	}	
}
