package com.example.contactManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MyConfig {

//	Authentication
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("admin").password(encoder.encode("Pwd1")).roles("ADMIN").build();
		UserDetails user = User.withUsername("user").password(encoder.encode("Pwd2")).roles("USER").build();
		
		return new InMemoryUserDetailsManager(admin,user);
	}
	
//	Authorization
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/contact/home","/contact/getuser","/contact/adduser").permitAll()
		.and()
		.authorizeHttpRequests()
		.requestMatchers("/contact/**","/user/**","/admin/**").authenticated()
		.and().formLogin()
		.and().build();		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	

}
