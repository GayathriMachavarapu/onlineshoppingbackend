package com.osa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osa.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
