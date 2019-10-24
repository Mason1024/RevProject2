package org.stuff.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.stuff.entities.Posting;
import org.stuff.entities.User;
import org.stuff.repo.PostingRepo;


@Component
@Service
public class PostingServiceImpl implements PostingService {
	
	
	@Autowired
	PostingRepo pr;

	@Override
	public Posting createPosting(Posting posting) {
		
		return pr.save(posting);
	}

	@Override
	public Posting getPostingById(int p_id) {
		
		return pr.findById(p_id).get();
	}

	@Override
	public Set<Posting> getAllPostingsByUser(User user) {
		
		return pr.findAllByUser(user);
	}

	@Override
	public List<Posting> getAllPostingByEndingSoonest() {
		
		return pr.findAllByOrderByEnd_dateDesc();
	}

	@Override
	public List<Posting> getNewestByRange(int lowerIndex, int upperIndex) {
		
		return pr.findAllByOrderByInit_dateDesc().subList(lowerIndex, upperIndex);
	}

	@Override
	public List<Posting> getAllPostings() {
		// make sure we test this thoroughly, i'm worried about casting straight from Iterable to List. -Aaron
		return (List<Posting>) pr.findAll();
	}

	@Override
	public Posting updatePosting(Posting posting) {
		
		return pr.save(posting);
	}

	@Override
	public boolean deletePosting(Posting posting) {
		
		try {
			pr.delete(posting);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
