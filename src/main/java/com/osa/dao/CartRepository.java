package com.osa.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osa.entity.Cart;
import com.osa.entity.User;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
	public List<Cart> findByUser(User user);

}
