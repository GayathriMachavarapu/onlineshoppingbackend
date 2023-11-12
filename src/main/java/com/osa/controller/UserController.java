package com.osa.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osa.entity.User;
import com.osa.exception.UserNameAlreadyExistsException;
import com.osa.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/adduser")
	public ResponseEntity<User> registerUser(@RequestBody User user)throws UserNameAlreadyExistsException{
		return new ResponseEntity<User>(userService.registerUser(user),HttpStatus.OK);
		
	}
	@PostConstruct
	public void initRolesAndUsers() {
		userService.initRolesAndUser();
	}
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "this URL is only for admin";
	}
	
	@GetMapping("/forUser")
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "this URL is only for User";
	}
	
//	public List<User> getUsers(){
//		return 
//	}
	@GetMapping("/getProfile/{userName}")
	
	public User getProfileByUserName(@PathVariable String userName) {
		return userService.getUserByUserName(userName);
	}
	@PreAuthorize("hasRole('User')")
	 @GetMapping("/my-profile")
	    public ResponseEntity<User> getMyProfile() {
	        // Retrieve authenticated user's username
	        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

	        // Fetch user details based on the username
	        User user = userService.getUserByUserName(userName);

	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
	
	
	
	@PreAuthorize("hasRole('User')")
	@PutMapping("/update-my-profile")
    public ResponseEntity<User> updateUserProfile(@RequestBody User updatedUser) {
        // Retrieve authenticated user's username
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        // Update user profile
        userService.updateUserProfile(userName, updatedUser);

        // Return updated user profile
        User user = userService.getUserByUserName(userName);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
