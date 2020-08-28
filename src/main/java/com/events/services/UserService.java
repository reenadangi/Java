package com.events.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.events.models.User;
import com.events.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public List<User> allUsers() {
		return userRepo.findAll();
	}

	// register user and hash their password
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
	}

	public User autheticateUser(String email, String password) {
		User user = userRepo.findByEmail(email);
		if (user == null) {
//    		No user in DB
			return null;
		}
		if (BCrypt.checkpw(password, user.getPassword())) {
			// password matched - user authenticated
			return user;
			
		} else {
			// password does not match
			return null;
		}
	}

	public User getUserById(Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
}
