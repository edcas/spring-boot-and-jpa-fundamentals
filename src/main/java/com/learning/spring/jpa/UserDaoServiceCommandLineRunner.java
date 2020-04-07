package com.learning.spring.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.spring.jpa.entity.User;
import com.learning.spring.jpa.service.UserDAOService;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);

	@Autowired
	private UserDAOService userDaoService;

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jack", "Admin");
		
		long userId = this.userDaoService.insert(user);
		
		log.info("New is created: " + userId);
	}

}
