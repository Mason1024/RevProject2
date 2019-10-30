package org.stuff.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.stuff.entities.User;
import org.stuff.entities.WebUser;
import org.stuff.repos.UserRepo;


@Component
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo ur;

	@Override
	public WebUser createUser(User user) {
		
		return new WebUser(ur.save(user));
	}

	public WebUser login(String username, String password) {
		User user = ur.findByUsername(username);
		if(user!=null && user.getPassword().equals(password)) {
			return new WebUser(user);
		}
		return null;
	}
	
	@Override
	public WebUser getUserById(int id) {
		
		return new WebUser(ur.findById(id).get());
	}

	@Override
	public WebUser getUserByUsername(String username) {
		
		return new WebUser(ur.findByUsername(username));
	}

	@Override
	public Set<WebUser> getAllUsers() {
		Set<WebUser> out = new HashSet<WebUser>();
		for(User user:ur.findAll()) {
			out.add(new WebUser(user));
		}
		return out;
	}

	@Override
	public WebUser updateUser(User user) {
		user.setPassword(ur.findById(user.getId()).get().getPassword());
		return new WebUser(ur.save(user));
	}

	@Override
	public boolean deleteUser(int id) {
		ur.delete(ur.findById(id).get());
		return !ur.existsById(id);
	}

}
