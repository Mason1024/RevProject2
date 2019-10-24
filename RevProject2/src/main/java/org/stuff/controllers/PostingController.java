package org.stuff.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stuff.entities.Posting;
import org.stuff.entities.User;
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
	
	
	@RequestMapping(value= "/postings", method= RequestMethod.GET)
	public List<Posting> getAllPostings(){
		return ps.getAllPostings();
	}
	
	
	
	
	
	// Update
	
	
	// Delete
	
}
