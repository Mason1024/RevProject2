package org.stuff.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.stuff.entities.User;

@Component
@Repository
public interface UserRepo extends CrudRepository<User,Integer>{

	User findByUsername(String username);
	User findByEmail(String email);
	
}
