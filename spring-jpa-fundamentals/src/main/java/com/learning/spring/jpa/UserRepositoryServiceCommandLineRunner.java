package com.learning.spring.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.spring.jpa.entity.User;
import com.learning.spring.jpa.service.UserRepository;

@Component
public class UserRepositoryServiceCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserRepositoryServiceCommandLineRunner.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jill", "Admin");
		
		this.userRepository.save(user);
		log.info("New is created: " + user);
		
		Optional<User> userWithIdOne = this.userRepository.findById(1L);
		log.info("User is retrived: " + userWithIdOne);
		
		List<User> users = this.userRepository.findAll();
		log.info("All users: " + users);
	}

}
