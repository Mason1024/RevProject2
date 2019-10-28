package org.stuff.services;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.stuff.entities.Posting;
import org.stuff.entities.User;
import org.stuff.repos.PostingRepo;


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
	public Set<Posting> getAllPostingsByCategory(String category){
		return pr.findAllByCategory(category);
	}

	@Override
	public List<Posting> getAllPostingByEndingSoonest() {
		List<Posting> items = pr.findAllByOrderByEndDateAsc();
		for(int i=0;i<items.size();i++) {
			if(items.get(0).getEndDate()==0) {
				items.remove(items.get(0));
			}else {
				break;
			}
		}
		return items;
	}

	@Override
	public List<Posting> getNewestByRange(int lowerIndex, int upperIndex) {
		
		return pr.findAllByOrderByInitDateDesc().subList(lowerIndex, upperIndex);
	}

	@Override
	public List<Posting> getAllPostings() {
		return new LinkedList<Posting>((Collection<? extends Posting>) pr.findAll());
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
