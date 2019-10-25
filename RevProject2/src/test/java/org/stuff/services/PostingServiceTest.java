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
		testPosting1.setInitDate(System.currentTimeMillis());
		testPosting1.setEndDate(System.currentTimeMillis()+10000);
		testPosting1.setUser(testUser1);
		testPosting1 = ps.createPosting(testPosting1);
		
		wasteTime();
		
		testPosting2 = new Posting();
		testPosting2.setTitle("PSTestItem2");
		testPosting2.setCategory("Table");
		testPosting2.setLocation("TestLocation");
		testPosting2.setInitDate(System.currentTimeMillis());
		testPosting2.setUser(testUser1);
		testPosting2 = ps.createPosting(testPosting2);

		wasteTime();
		
		testPosting3 = new Posting();
		testPosting3.setTitle("PSTestItem3");
		testPosting3.setCategory("Table");
		testPosting3.setLocation("TestLocation");
		testPosting3.setInitDate(System.currentTimeMillis());
		testPosting3.setEndDate(System.currentTimeMillis()+10000);
		testPosting3.setUser(testUser1);
		testPosting3 = ps.createPosting(testPosting3);

		wasteTime();
		
		testPosting4 = new Posting();
		testPosting4.setTitle("PSTestItem4");
		testPosting4.setCategory("Table");
		testPosting4.setLocation("TestLocation");
		testPosting4.setInitDate(System.currentTimeMillis());
		testPosting4.setUser(testUser2);
		testPosting4 = ps.createPosting(testPosting4);

		wasteTime();
		
		testPosting5 = new Posting();
		testPosting5.setTitle("PSTestItem5");
		testPosting5.setCategory("Table");
		testPosting5.setLocation("TestLocation");
		testPosting5.setInitDate(System.currentTimeMillis());
		testPosting5.setEndDate(System.currentTimeMillis()+10000);
		testPosting5.setUser(testUser2);
		testPosting5 = ps.createPosting(testPosting5);

		wasteTime();
		
		testPosting6 = new Posting();
		testPosting6.setTitle("PSTestItem6");
		testPosting6.setCategory("Table");
		testPosting6.setLocation("TestLocation");
		testPosting6.setInitDate(System.currentTimeMillis());
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
		for(Posting item:items) {
			assertTrue(item.equals(testPosting1)||item.equals(testPosting2)||item.equals(testPosting3), "Incorrect item found "+item);
		}
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
		assertTrue(items.get(0).equals(testPosting4));
		assertTrue(items.get(1).equals(testPosting3));
	}
	@Test
	@Order(2)
	void getAllPostings() {
		List<Posting> items = ps.getAllPostings();
		assertTrue(items.size()>=6, "6 posting delcared, at least 6 posting should be found");
		for(Posting item:items) {
			assertTrue(	item.equals(testPosting1)||
						item.equals(testPosting2)||
						item.equals(testPosting3)||
						item.equals(testPosting4)||
						item.equals(testPosting5)||
						item.equals(testPosting6));
		}
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
