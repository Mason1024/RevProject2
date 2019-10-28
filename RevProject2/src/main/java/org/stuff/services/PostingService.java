package org.stuff.services;

import java.util.List;
import java.util.Set;

import org.stuff.entities.Posting;
import org.stuff.entities.User;

public interface PostingService {
	
	
	// Create
	
	Posting createPosting(Posting posting);
	
	
	
	// Read
	
	Posting getPostingById(int p_id);
	Set<Posting> getAllPostingsByUser(User user);
	Set<Posting> getAllPostingsByCategory(String category);
	List<Posting> getAllPostingByEndingSoonest();
	/**
	 * @param lowerIndex index 0, inclusive
	 * @param upperIndex exclusive
	 * @return
	 */
	List<Posting> getNewestByRange(int lowerIndex, int upperIndex);
	List<Posting> getAllPostings();
	
	// Update
	
	Posting updatePosting(Posting posting);	
	
	
	// Delete
	
	boolean deletePosting(Posting posting);

}
