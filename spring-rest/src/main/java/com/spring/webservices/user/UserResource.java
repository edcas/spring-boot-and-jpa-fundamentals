package com.spring.webservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
 

@SuppressWarnings("deprecation")
@RestController
public class UserResource {

	@Autowired
	private UserDaoService userService;

	@GetMapping("/users")
	public List<User> all() {
		return this.userService.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {

		User user = this.userService.findOne(id);

		if (user == null)

			throw new UserNotFoundException("id-" + id);

		EntityModel<User> model = new EntityModel<>(user);

		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).all());

		model.add(linkTo.withRel("all-users"));

		return model;

	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = this.userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable int id) {

		User user = this.userService.deleteById(id);

		if (user == null) {
			throw new UserNotFoundException("id- " + id);
		}

	}
}
