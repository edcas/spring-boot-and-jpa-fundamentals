package com.spring.webservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userService;
	
	@GetMapping("/users")
	public List<User> all() {
		return this.userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User show(@PathVariable int id) {
		return this.userService.findOne(id);
	}
	
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		User savedUser = this.userService.save(user);
	}
}
