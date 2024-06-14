package com.example.contactManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService  getUserDetailService(BCryptPasswordEncoder passwordEncoder) {
		
//		UserDetails admin =User.withUsername("priyanka").password(passwordEncoder.encode("priyanka")).roles("ADMIN").build();
//		
//		UserDetails user =User.withUsername("adesh").password(passwordEncoder.encode("adesh")).roles("USER").build();
//		
		return new UserDetailServiceImpl();
	}
	
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity.csrf().disable().authorizeHttpRequests()
		.requestMatchers("/**")
		.permitAll()
		.and()
		.authorizeHttpRequests()
		.requestMatchers("/admin/**").authenticated()
		.and()
		.authorizeHttpRequests()
		.requestMatchers("/user/**").authenticated()
		.and().formLogin().and().build();
		
	}
	
	
	

}
