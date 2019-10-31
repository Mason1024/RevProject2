package org.stuff.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stuff.entities.Posting;
import org.stuff.entities.User;
import org.stuff.entities.WebPosting;
import org.stuff.services.PostingService;
import org.stuff.services.UserService;

@Component
@RestController
public class PostingController {
	// /getPostById/{ID}
	
	@Autowired
	PostingService ps;
	
	@Autowired
	UserService us;
	
	// Create
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/postings", method= RequestMethod.POST)
	public WebPosting createPosting(@RequestBody Posting posting) {
		return ps.createPosting(posting);
	}
	
	// Read
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/postings/{ID}", method= RequestMethod.GET)
	public WebPosting getPostingById(@PathVariable int ID) {
		return ps.getPostingById(ID);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/postings/allbyuser/{ID}", method= RequestMethod.GET)
	public Set<WebPosting> getPostingByUser(@PathVariable int ID) {
		return ps.getAllPostingsByUser(new User(us.getUserById(ID)));
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/postings/allbycategory/{category}", method= RequestMethod.GET)
	public Set<WebPosting> getAllPostingByCategory(@PathVariable String category){
		return ps.getAllPostingsByCategory(category);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/postings", method= RequestMethod.GET)
	public List<WebPosting> getAllPostings(){
		return ps.getAllPostings();
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/postings/end", method= RequestMethod.GET)
	public List<WebPosting> getPostingsEndingSoonest(){
		return ps.getAllPostingByEndingSoonest();
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/postings/range/{lower}/{upper}", method= RequestMethod.GET)
	public List<WebPosting> getPostingsByRange(@PathVariable int lower, @PathVariable int upper){
		return this.getAllPostings().subList(lower, upper);
	}
	
	
	// Update
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/postings", method= RequestMethod.PUT)
	public WebPosting updatePosting(@RequestBody Posting posting){
		return ps.updatePosting(posting);
	}
	
	
	// Delete
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/postings/{id}", method= RequestMethod.DELETE)
	public boolean deletePosting(@PathVariable int id) {
		return ps.deletePosting(id);
	}
	
}
