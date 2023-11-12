package com.osa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osa.entity.OrderDetail;
import com.osa.entity.User;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	public List<OrderDetail> findByUser(User user);
	public List<OrderDetail> findByOrderStatus(String status);

}
