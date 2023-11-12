package com.osa.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.osa.dao.RoleRepository;
import com.osa.dao.UserRepository;
import com.osa.entity.Role;
import com.osa.entity.User;
import com.osa.exception.UserNameAlreadyExistsException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public User registerUser(User user) throws UserNameAlreadyExistsException{
		Optional<User> user1=userRepository.findById(user.getUserName());
		
		if(user1.isPresent()) {
			throw new UserNameAlreadyExistsException("user Already Exists");
		}
        Role role=roleRepository.findById("User").get();
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		user.setRole(roles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		
		return userRepository.save(user);
	}
	
	public void initRolesAndUser() {
		Role adminRole=new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin role");
		roleRepository.save(adminRole);
		
		Role userRole=new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("User role");
	    roleRepository.save(userRole);
	    
	    
	    User adminUser=new User();
	    adminUser.setFirstName("Admin");
	    adminUser.setLastName("Admin");
	    adminUser.setUserName("Admin123");
	    adminUser.setUserPassword(getEncodedPassword("admin@123"));
	    Set<Role> adminRoles=new HashSet<>();
	    adminRoles.add(adminRole);
	    adminUser.setRole(adminRoles);
	    userRepository.save(adminUser);
	    
	    User user=new User();
	    user.setFirstName("Gayathri");
	    user.setLastName("M");
	    user.setUserName("Gayathri123");
	    user.setUserPassword(getEncodedPassword("gayu"));
	    Set<Role> uRoles=new HashSet<>();
	    uRoles.add(userRole);
	    user.setRole(uRoles);
	    userRepository.save(user);
		
		
		
		
		
	}
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	
	public User getUserByUserName(String userName) {
		User user=userRepository.findById(userName).get();
		return user;
	}

	public void updateUserProfile(String userName, User updatedUser) {
		// TODO Auto-generated method stub
		 User existingUser = userRepository.findById(userName).get();
	        if (existingUser != null) {
	        	  
	            existingUser.setFirstName(updatedUser.getFirstName());
	            existingUser.setLastName(updatedUser.getLastName());

	            // Update other fields as needed
	            userRepository.save(existingUser);
	        }
	    }
	
	
	

}
	
