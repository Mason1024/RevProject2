package org.stuff.repos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
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
import org.stuff.repos.PostingRepo;
import org.stuff.repos.UserRepo;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes=org.stuff.app.RevProject2Application.class)
public class PostingRepoTest {

	@Autowired
	PostingRepo pr;
	
	@Autowired
	UserRepo ur;
	
	static User testUser1;
	static User testUser2;
	static Posting testPosting1;
	static Posting testPosting2;
	static Posting testPosting3;
	
	@Test
	@Order(1)
	@Commit
	void setup() {
		testUser1 = new User();
		testUser1.setUsername("PRTestUser1");
		testUser1.setPassword("Pa$$word");
		testUser1.setEmail("TestUser1@test.com");
		testUser1.setPhoneNumber("1234567890");
		testUser1 = ur.save(testUser1);
		
		testUser2 = new User();
		testUser2.setUsername("PRTestUser2");
		testUser2.setPassword("Pa$$word");
		testUser2.setEmail("TestUser2@test.com");
		testUser2.setPhoneNumber("1234567890");
		testUser2 = ur.save(testUser2);
		
		testPosting1 = new Posting();
		testPosting1.setTitle("Item1");
		testPosting1.setDescription("This is a test item");
		testPosting1.setCategory("Table");
		testPosting1.setLocation("Test Location");
		testPosting1.setInitDate(Long.MAX_VALUE-3);
		testPosting1.setEndDate(1);
		testPosting1.setUser(testUser1);
		testPosting1 = pr.save(testPosting1);
		
		testPosting2 = new Posting();
		testPosting2.setTitle("Item2");
		testPosting2.setDescription("This is a test item");
		testPosting2.setCategory("Chair");
		testPosting2.setLocation("Test Location");
		testPosting2.setInitDate(Long.MAX_VALUE-2);
		testPosting2.setEndDate(2);
		testPosting2.setUser(testUser1);
		testPosting2 = pr.save(testPosting2);
		
		testPosting3 = new Posting();
		testPosting3.setTitle("Item3");
		testPosting3.setDescription("This is a test item");
		testPosting3.setCategory("Table");
		testPosting3.setLocation("Test Location");
		testPosting3.setInitDate(Long.MAX_VALUE-1);
		testPosting3.setEndDate(3);
		testPosting3.setUser(testUser2);
		testPosting3 = pr.save(testPosting3);
		
	}
	
	@Test
	@Order(2)
	void findAllByUser() {
		Set<Posting> items = pr.findAllByUser(testUser1);
		assertTrue(items.contains(testPosting1), "Set has item 1 "+testPosting1);
		assertTrue(items.contains(testPosting2), "Set has item 2 "+testPosting1);
		assertTrue(!items.contains(testPosting3), "Set does not have item 3 "+testPosting1);
	}
	
	@Test
	@Order(2)
	void findAllByCategory() {
		Set<Posting> items = pr.findAllByCategory("Table");
		assertTrue(items.contains(testPosting1), "Set has item 1 "+testPosting1);
		assertTrue(items.contains(testPosting3), "Set has item 3 "+testPosting3);
		assertTrue(!items.contains(testPosting2), "Set does not have item 2 "+testPosting2);
	}
	
	@Test
	@Order(2)
	void findAllByOrderByInitDateDesc() {
		List<Posting> items = pr.findAllByOrderByInitDateDesc();
		assertTrue(items.get(0).equals(testPosting3), "List has item 3");
		assertTrue(items.get(1).equals(testPosting2), "List has item 2");
		assertTrue(items.get(2).equals(testPosting1), "List has item 1");
	}
	
	@Test
	@Order(2)
	void findAllByOrderByEndDateAsc() {
		List<Posting> items = new LinkedList<Posting>();
		
		//remove the items without end date (0 time)
		for(Posting item:pr.findAllByOrderByEndDateAsc()) {
			if(item.getEndDate()!=0) {
				items.add(item);
			}
		}
		
		assertTrue(items.get(0).equals(testPosting1), "List has item 1");
		assertTrue(items.get(1).equals(testPosting2), "List has item 2");
		assertTrue(items.get(2).equals(testPosting3), "List has item 3");
	}
	
	@Test
	@Order(3)
	@Commit
	void teardown() {
		pr.delete(testPosting1);
		pr.delete(testPosting2);
		pr.delete(testPosting3);
		ur.delete(testUser1);
		ur.delete(testUser2);
	}
}
