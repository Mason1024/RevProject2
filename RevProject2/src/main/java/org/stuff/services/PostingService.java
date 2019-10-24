package org.stuff.services;

import java.util.List;

import org.stuff.entities.Posting;

public interface PostingService {
	
	
	// Create
	
	Posting createPosting(Posting posting);
	
	
	
	// Read
	
	Posting getPostingById(int p_id);
	Posting getPostingByUserId(int u_id);
	List<Posting> getAllPostingByEndingSoonest();
	
	
	// Update
	
	Posting updatePosting(Posting posting);	
	
	
	// Delete
	
	boolean deletePosting(Posting posting);

}
