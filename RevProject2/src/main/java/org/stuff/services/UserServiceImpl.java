package org.stuff.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.stuff.entities.User;
import org.stuff.repos.UserRepo;


@Component
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo ur;

	@Override
	public User createUser(User user) {
		
		return ur.save(user);
	}

	public User login(String username, String password) {
		User user = this.getUserByUsername(username);
		if(user!=null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	@Override
	public User getUserById(int id) {
		
		return ur.findById(id).get();
	}

	@Override
	public User getUserByUsername(String username) {
		
		return ur.findByUsername(username);
	}

	@Override
	public Set<User> getAllUsers() {
		
		return new HashSet<User>((Collection<? extends User>) ur.findAll());
	}

	@Override
	public User updateUser(User user) {
		
		return ur.save(user);
	}

	@Override
	public boolean deleteUser(User user) {
		

			ur.delete(user);
			return !ur.existsById(user.getId());

		
		
	}

}
