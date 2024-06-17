package com.example.contactManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.contactManager.entity.User;
import com.example.contactManager.helper.Message;
import com.example.contactManager.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/contact")
public class HomeController {
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
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
	
	@GetMapping("/getuser")
	@ResponseBody
	public List<User> getUser() {
		
		List<User> all = (List<User>) userRepository.findAll();
		return all;
	}
	
	
	@PostMapping("/adduser")
	@ResponseBody
	public User addUser(@RequestBody User user) {
		
		User saveuser = userRepository.save(user);
		return saveuser;
	}
	
	
	
	@GetMapping("/loginuser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String login(Model model) {
		model.addAttribute("Title", "Login - Contact manager");
		
		return "login";
	}
	
	@GetMapping("/signup")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String signup(Model model) {
		model.addAttribute("Title", "SignUp - Contact manager");
		User user =new User();
		model.addAttribute("user", user);
		return "signup";
	}
	
	
	@PostMapping("/do_register")
	public String register ( @Valid @ModelAttribute("user") User user,BindingResult bindingResult , @RequestParam(value="agree", defaultValue="false") boolean agree, Model model,HttpSession session) {
		
	
		try {
			
			if(bindingResult.hasErrors()) {
				System.out.println(bindingResult);
				return "signup";
			}
			
			
			if(!agree) {
				
				System.out.println("You have not agree the terms and conditions!!");
				throw new Exception("You have not agree the terms and conditions!!");
			}
	
			user.setRole("Role_User");
			user.setActive(true);
			user.setProfile("default.png");
//			user.setPassword(passwordEncoder.encode( user.getPassword()));
			
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
