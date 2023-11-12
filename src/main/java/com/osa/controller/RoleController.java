package com.osa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osa.entity.Role;
import com.osa.exception.RoleAlreadyExistsException;
import com.osa.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@PostMapping("/addrole")
	public ResponseEntity<Role> addRole(@RequestBody Role role)throws RoleAlreadyExistsException{
		return new ResponseEntity<Role>(roleService.addRole(role),HttpStatus.ACCEPTED);
		
	}

}
