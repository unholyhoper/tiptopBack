package com.tiptop.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiptop.users.entities.Role;
import com.tiptop.users.entities.User;
import com.tiptop.users.repos.RoleRepository;
import com.tiptop.users.repos.UserRepository;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public User saveUser(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findUserByUsername(String username) {
			return userRepository.findByUsername(username);
	}

	@Override
	public Role addRole(Role role) {
			return roleRepository.save(role);
	}

	@Override
	public User addRoleToUser(String username, String rolename) {
		User usr = userRepository.findByUsername(username);
		Role role = roleRepository.findByRole(rolename);
		
		usr.getRoles().add(role);
		
		//userRepository.save(usr);
			
		return usr;
	}
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getYearWinner() {
		return userRepository.findRandomUser();
	}


	@Override
	public Long getUserIdByUsername(String username){
		User user = userRepository.findByUsername(username); // Assuming you have a method in your repository to find a user by their username
		if (user != null) {
			return user.getUser_id();
		} else {
			// Handle the case where the user is not found
			return null; // or throw an exception
		}
	}



}
