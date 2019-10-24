package org.stuff.repo;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.stuff.entities.Posting;

@Component
@Repository
public interface PostingRepo extends CrudRepository<Posting,Integer>{

	Set<Posting> findAllByU_id(int u_id);
	Set<Posting> findAllByCategory(String category);
	
}
