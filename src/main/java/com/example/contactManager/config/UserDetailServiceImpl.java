package com.example.contactManager.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.contactManager.entity.User;
import com.example.contactManager.repository.UserRepository;

//@Component
public class UserDetailServiceImpl{
//implements UserDetailsService{
	
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//		
//		Optional<User> user = userRepository.findByName(name);
		
//		return user.map(CustomUserDetails :: new).orElseThrow(()-> new UsernameNotFoundException("User not found" + name));
//	}

}
