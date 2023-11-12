package com.osa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.osa.entity.OrderDetail;
import com.osa.entity.OrderInput;
import com.osa.entity.TransactionDetails;
import com.osa.service.OrderDetailService;
import com.razorpay.Order;

@RestController
@CrossOrigin
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;
	
	@PreAuthorize("hasRole('User')")
	@PostMapping("/placeOrder/{isSingleProductCheckout}")
	public void placeOrder(@RequestBody OrderInput orderInput,@PathVariable boolean isSingleProductCheckout) {
		orderDetailService.placeOrder(orderInput,isSingleProductCheckout);
		
	}
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getOrderDetails")
	public List<OrderDetail> getOrderDetails() {
		return orderDetailService.getOrderDetails();
	}
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getAllOrderDetails/{status}")
	public List<OrderDetail> getAllOrderDetails(@PathVariable String status) {
		return orderDetailService.getAllOrderDetails(status);
	}
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/markAsDelivered/{orderId}")
	public void markOrderAsDelivered(@PathVariable int orderId) {
			orderDetailService.markOrderAsDelivered(orderId);
	}
	@PreAuthorize("hasRole('User')")
	@GetMapping("/createTransaction/{amount}")
	public TransactionDetails  createTransaction(@PathVariable double amount) {
		return orderDetailService.createTransaction(amount);
		
	}
	
	
	
	
	

}
