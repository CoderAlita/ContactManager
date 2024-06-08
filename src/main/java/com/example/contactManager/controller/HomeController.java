package com.example.contactManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.contactManager.entity.User;


@Controller
public class HomeController {
	
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
	public String register(@ModelAttribute("user") User user, @RequestParam(value="agree", defaultValue="false") boolean agree, Model model ,BindingResult result) {
		
		if(result.hasErrors()) {
					
					System.out.println(result);
				}
		
		System.out.println("User  "+user);
		System.out.println("Agree  " + agree);
		
		return "registered";
	}

}
