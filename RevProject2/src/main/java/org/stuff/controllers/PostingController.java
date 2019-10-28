package org.stuff.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stuff.entities.Posting;
import org.stuff.services.PostingService;

@Component
@RestController
public class PostingController {
	// /getPostById/{ID}
	
	@Autowired
	PostingService ps;
	
	
	// Create
	@RequestMapping(value= "/postings", method= RequestMethod.POST)
	public Posting createPosting(@RequestBody Posting posting) {
		return ps.createPosting(posting);
	}
	
	
	// Read
	@RequestMapping(value= "/postings/{ID}", method= RequestMethod.GET)
	public Posting getPostingById(@PathVariable int ID) {
		return ps.getPostingById(ID);
	}
	
	@RequestMapping(value= "/postings/allbyuser/{ID}", method= RequestMethod.GET)
	public Posting getPostingByUser(@PathVariable int ID) {
		return ps.getPostingById(ID);
	}
	
	@RequestMapping(value= "/postings", method= RequestMethod.GET)
	public List<Posting> getAllPostings(){
		return ps.getAllPostings();
	}
	
	
	@RequestMapping(value= "/postings/end", method= RequestMethod.GET)
	public List<Posting> getPostingsEndingSoonest(){
		return ps.getAllPostingByEndingSoonest();
	}
	
	@RequestMapping(value= "/postings/range/{lower}/{upper}", method= RequestMethod.GET)
	public List<Posting> getPostingsByRange(@PathVariable int lower, @PathVariable int upper){
		return this.getAllPostings().subList(lower, upper);
	}
	
	
	// Update
	
	@RequestMapping(value= "/postings", method= RequestMethod.PUT)
	public Posting updatePosting(@RequestBody Posting posting){
		return ps.updatePosting(posting);
	}
	
	
	// Delete
	
	@RequestMapping(value= "/postings/{id}", method= RequestMethod.DELETE)
	public boolean deletePosting(@PathVariable int id) {
		return ps.deletePosting(ps.getPostingById(id));
	}
	
}
