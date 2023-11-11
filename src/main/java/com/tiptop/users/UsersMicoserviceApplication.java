package com.tiptop.users;

import com.tiptop.users.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class UsersMicoserviceApplication {
	@Autowired
	UserServiceImpl userService;


	public static void main(String[] args) {
		SpringApplication.run(UsersMicoserviceApplication.class, args);
	}
	@PostConstruct
	void init_users() {

	//userService.addRoleToUser("admin", "ADMIN");
		}
	@Bean
	BCryptPasswordEncoder getBCE() {
		return new BCryptPasswordEncoder();
	}
	
	

}
