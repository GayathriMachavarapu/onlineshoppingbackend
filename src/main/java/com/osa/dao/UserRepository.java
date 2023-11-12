package com.osa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
