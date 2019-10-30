package org.stuff.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.stuff.entities.Posting;
import org.stuff.entities.User;
import org.stuff.entities.WebPosting;
import org.stuff.repos.PostingRepo;


@Component
@Service
public class PostingServiceImpl implements PostingService {
	
	
	@Autowired
	PostingRepo pr;

	@Override
	public WebPosting createPosting(Posting posting) {
		
		return new WebPosting(pr.save(posting));
	}

	@Override
	public WebPosting getPostingById(int p_id) {
		
		return new WebPosting(pr.findById(p_id).get());
	}

	@Override
	public Set<WebPosting> getAllPostingsByUser(User user) {
		Set<WebPosting> out = new HashSet<WebPosting>();
		for(Posting post:pr.findAllByUser(user)) {
			out.add(new WebPosting(post));
		}
		return out;
	}

	@Override
	public Set<WebPosting> getAllPostingsByCategory(String category){
		Set<WebPosting> out = new HashSet<WebPosting>();
		for(Posting post:pr.findAllByCategory(category)) {
			out.add(new WebPosting(post));
		}
		return out;
	}

	@Override
	public List<WebPosting> getAllPostingByEndingSoonest() {
		List<WebPosting> out = new LinkedList<WebPosting>();
		for(Posting post:pr.findAllByOrderByEndDateAsc()){
			if(post.getEndDate()!=0) {
				out.add(new WebPosting(post));
			}
		}
		return out;
	}

	@Override
	public List<WebPosting> getNewestByRange(int lowerIndex, int upperIndex) {
		List<WebPosting> out = new LinkedList<WebPosting>();
		for(Posting post:pr.findAllByOrderByInitDateDesc().subList(lowerIndex, upperIndex)) {
			out.add(new WebPosting(post));
		}
		return out;
	}

	@Override
	public List<WebPosting> getAllPostings() {		
		List<WebPosting> out = new LinkedList<WebPosting>();
		for(Posting post:pr.findAll()) {
			out.add(new WebPosting(post));
		}
		return out;
	}

	@Override
	public WebPosting updatePosting(Posting posting) {
		return new WebPosting(pr.save(posting));
	}

	@Override
	public boolean deletePosting(int id) {
		try {
			pr.delete(pr.findById(id).get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
