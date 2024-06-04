package com.example.contactManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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

}
