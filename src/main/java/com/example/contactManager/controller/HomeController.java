package com.example.contactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.contactManager.entity.User;
import com.example.contactManager.helper.Message;
import com.example.contactManager.repository.UserRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Message message;
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("Title", "Home - Contact manager");
		return "Home";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("Title", "About - Contact manager");
		return "About";
	}
	
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("Title", "Login - Contact manager");
		
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("Title", "SignUp - Contact manager");
		User user =new User();
		model.addAttribute("user", user);
		return "signup";
	}
	
	
	@PostMapping("/do_register")
	public String register(@ModelAttribute("user") User user, @RequestParam(value="agree", defaultValue="false") boolean agree, Model model,HttpSession session ) {
		
	
		try {
			
			if(!agree) {
				
				System.out.println("You have not agree the terms and conditions!!");
				throw new Exception("You have not agree the terms and conditions!!");
			}
	
			user.setRole("Role_User");
			user.setActive(true);
			user.setProfile("default.png");
			
			System.out.println("User  "+user);
			System.out.println("Agree  " + agree);
			
			User result = userRepository.save(user);
			model.addAttribute("user", new User());
			
			message.setContent("Registered successfullly !");
			message.setType("alert-success");
			
			session.setAttribute("message", message);
			
			return "signup";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			
			message.setContent("Something went wrong !" + e.getMessage());
			message.setType("alert-danger");
			session.setAttribute("message", message);
			
			return "signup";
		}
		
		
	}

}
