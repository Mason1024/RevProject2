package org.stuff.services;

import java.util.List;
import java.util.Set;

import org.stuff.entities.Posting;
import org.stuff.entities.User;
import org.stuff.entities.WebPosting;

public interface PostingService {
	
	
	// Create
	
	WebPosting createPosting(Posting posting);
	
	
	
	// Read
	
	WebPosting getPostingById(int p_id);
	Set<WebPosting> getAllPostingsByUser(User user);
	Set<WebPosting> getAllPostingsByCategory(String category);
	List<WebPosting> getAllPostingByEndingSoonest();
	/**
	 * @param lowerIndex index 0, inclusive
	 * @param upperIndex exclusive
	 * @return
	 */
	List<WebPosting> getNewestByRange(int lowerIndex, int upperIndex);
	List<WebPosting> getAllPostings();
	
	// Update
	
	WebPosting updatePosting(Posting posting);	
	
	
	// Delete
	
	boolean deletePosting(int id);

}
