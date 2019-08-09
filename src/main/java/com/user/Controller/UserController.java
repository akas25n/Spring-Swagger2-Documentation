package com.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.Exception.UserNotFoundException;
import com.user.Model.User;
import com.user.Service.UserService;

@RestController
public class UserController {	

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return userService.findall();
	}
	
	@GetMapping("/users/{id}")
	public User findSinleUser(@PathVariable int id) {
		User user = userService.findOne(id); 
		if (user==null) {
			throw new UserNotFoundException("id-" + id);
		}
		return user;
	}
	
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {	
		userService.save(user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);
		
		if (user==null) {
			throw new UserNotFoundException("id-" + id);
		}
	}

}
