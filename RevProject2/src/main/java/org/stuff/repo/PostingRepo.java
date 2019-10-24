package org.stuff.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.stuff.entities.Posting;
import org.stuff.entities.User;

@Component
@Repository
public interface PostingRepo extends CrudRepository<Posting,Integer>{

	Set<Posting> findAllByUser(User user);
	Set<Posting> findAllByCategory(String category);
	List<Posting> findAllByOrderByInitDateDesc();
	List<Posting> findAllByOrderByEndDateDesc();
	
}
