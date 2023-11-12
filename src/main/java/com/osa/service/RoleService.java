package com.osa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osa.dao.RoleRepository;
import com.osa.entity.Role;
import com.osa.exception.RoleAlreadyExistsException;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	public Role addRole(Role role) throws RoleAlreadyExistsException{
		Optional<Role> role1=roleRepository.findById(role.getRoleName());
		if(role1.isPresent()) {
			throw new RoleAlreadyExistsException("Role already exists");
		}
		return roleRepository.save(role);
		
	}

}
