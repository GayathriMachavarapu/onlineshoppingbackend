package com.osa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.osa.entity.Cart;
import com.osa.service.CartService;

@RestController
public class CartController {
	@Autowired
	private CartService cartService;
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/addToCart/{productId}")
	public Cart addToCart(@PathVariable int productId) {
		return cartService.addToCart(productId);
		
	}
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getCartDetails")
	public List<Cart> getCartDetails() {
		return cartService.getCartDeatils();
		
	}
	
	@PreAuthorize("hasRole('User')")
	@DeleteMapping("/deleteCartItem/{cartId}")
	public void deleteCartItem(@PathVariable int cartId) {
		cartService.deleteCartItem(cartId);
		
	}

}
