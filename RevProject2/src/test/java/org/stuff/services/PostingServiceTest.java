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
	
	static User testUser1;
	static User testUser2;
	
	static Posting testPosting1;
	static Posting testPosting2;
	static Posting testPosting3;
	static Posting testPosting4;
	static Posting testPosting5;
	static Posting testPosting6;
	
	@Test
	@Order(1)
	@Commit
	void setup() {
		testUser1 = new User();
		testUser1.setUsername("PSTestUser1");
		testUser1.setPassword("TestUser");
		testUser1 = us.createUser(testUser1);
		
		testUser2 = new User();
		testUser2.setUsername("PSTestUser2");
		testUser2.setPassword("TestUser");
		testUser2 = us.createUser(testUser2);
		
		testPosting1 = new Posting();
		testPosting1.setTitle("PSTestItem1");
		testPosting1.setCategory("Table");
		testPosting1.setLocation("TestLocation");
		testPosting1.setInitDate(Long.MAX_VALUE-6);
		testPosting1.setEndDate(1);
		testPosting1.setUser(testUser1);
		testPosting1 = ps.createPosting(testPosting1);
		
		testPosting2 = new Posting();
		testPosting2.setTitle("PSTestItem2");
		testPosting2.setCategory("Chair");
		testPosting2.setLocation("TestLocation");
		testPosting2.setInitDate(Long.MAX_VALUE-5);
		testPosting2.setUser(testUser1);
		testPosting2 = ps.createPosting(testPosting2);
		
		testPosting3 = new Posting();
		testPosting3.setTitle("PSTestItem3");
		testPosting3.setCategory("Chair");
		testPosting3.setLocation("TestLocation");
		testPosting3.setInitDate(Long.MAX_VALUE-4);
		testPosting3.setEndDate(2);
		testPosting3.setUser(testUser1);
		testPosting3 = ps.createPosting(testPosting3);
		
		testPosting4 = new Posting();
		testPosting4.setTitle("PSTestItem4");
		testPosting4.setCategory("Table");
		testPosting4.setLocation("TestLocation");
		testPosting4.setInitDate(Long.MAX_VALUE-3);
		testPosting4.setUser(testUser2);
		testPosting4 = ps.createPosting(testPosting4);
		
		testPosting5 = new Posting();
		testPosting5.setTitle("PSTestItem5");
		testPosting5.setCategory("Chair");
		testPosting5.setLocation("TestLocation");
		testPosting5.setInitDate(Long.MAX_VALUE-2);
		testPosting5.setEndDate(3);
		testPosting5.setUser(testUser2);
		testPosting5 = ps.createPosting(testPosting5);
		
		testPosting6 = new Posting();
		testPosting6.setTitle("PSTestItem6");
		testPosting6.setCategory("Table");
		testPosting6.setLocation("TestLocation");
		testPosting6.setInitDate(Long.MAX_VALUE-1);
		testPosting6.setUser(testUser2);
		testPosting6 = ps.createPosting(testPosting6);
	}
	
	@Test
	@Order(2)
	void getPostingById() {
		Posting found = ps.getPostingById(testPosting1.getId());
		assertTrue(found!=null);
		assertTrue(found.equals(testPosting1));
	}
	@Test
	@Order(2)
	void getAllPostingsByUser() {
		Set<Posting> items = ps.getAllPostingsByUser(testUser1);
		assertTrue(items.contains(testPosting1));
		assertTrue(items.contains(testPosting2));
		assertTrue(items.contains(testPosting3));
	}
	@Test
	@Order(2)
	void getAllPostingsByCategory() {
		Set<Posting> items = ps.getAllPostingsByCategory("Table");
		assertTrue(items.contains(testPosting1));
		assertTrue(items.contains(testPosting4));
		assertTrue(items.contains(testPosting6));
	}
	@Test
	@Order(2)
	void getAllPostingByEndingSoonest() {
		List<Posting> items = ps.getAllPostingByEndingSoonest();
		assertTrue(items.get(0).equals(testPosting1));
		assertTrue(items.get(1).equals(testPosting3));
		assertTrue(items.get(2).equals(testPosting5));
	}
	@Test
	@Order(2)
	void getNewestByRange() {
		List<Posting> items = ps.getNewestByRange(2, 4);
		assertTrue(items.get(0).equals(testPosting4), "Item 2");
		assertTrue(items.get(1).equals(testPosting3), "Item 3");
	}
	@Test
	@Order(2)
	void getAllPostings() {
		List<Posting> items = ps.getAllPostings();
		assertTrue(items.contains(testPosting1));
		assertTrue(items.contains(testPosting2));
		assertTrue(items.contains(testPosting3));
		assertTrue(items.contains(testPosting4));
		assertTrue(items.contains(testPosting5));
		assertTrue(items.contains(testPosting6));
	}
	@Test
	@Order(2)
	void updatePosting() {
		String oldLoc = testPosting1.getLocation();
		String newLoc = "NewLocation";
		testPosting1.setLocation(newLoc);
		Posting temp = ps.updatePosting(testPosting1);
		
		assertTrue(temp!=null);
		assertTrue(temp.getLocation().equals(newLoc));
		
		testPosting1.setLocation(oldLoc);
	}
	
	@Test
	@Order(3)
	@Commit
	void teardown() {
		ps.deletePosting(testPosting1);
		ps.deletePosting(testPosting2);
		ps.deletePosting(testPosting3);
		ps.deletePosting(testPosting4);
		ps.deletePosting(testPosting5);
		ps.deletePosting(testPosting6);
		
		us.deleteUser(testUser1);
		us.deleteUser(testUser2);
	}
	
	private void wasteTime() {
		long end = System.currentTimeMillis()+1000;
		while(System.currentTimeMillis()<end) {}
	}
	
}
