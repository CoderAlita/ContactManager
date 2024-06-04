package com.example.contactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.contactManager.entity.ContactEntity;
import com.example.contactManager.entity.UserEntity;
import com.example.contactManager.repository.UserRepository;

@Controller
public class MagaerController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/test")
	@ResponseBody
	public String home() {
		
		UserEntity user = new UserEntity();
		user.setName("Priya");
		user.setEmail("priya@gmail.com");
		userRepository.save(user);
		
		ContactEntity contact=new ContactEntity();
		contact.setName("Manohar");
		contact.setEmail("manohar03@gmail.com");
		
		return "Contact Manager";
	}
}
