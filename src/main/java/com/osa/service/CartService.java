package com.osa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.osa.configuration.JwtRequestFilter;
import com.osa.dao.CartRepository;
import com.osa.dao.ProductRepository;
import com.osa.dao.UserRepository;
import com.osa.entity.Cart;
import com.osa.entity.Product;
import com.osa.entity.User;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;

	public Cart addToCart(int productId) {
		Product product = productRepository.findById(productId).get();
		String userName = JwtRequestFilter.CURRENT_USER;
		User user = null;
		user = userRepository.findById(userName).get();
		List<Cart> cartList = cartRepository.findByUser(user);
		List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId)
				.collect(Collectors.toList());
		if (filteredList.size() > 0) {
			return null;
		}
		if (product != null && userName != null) {
			Cart cart = new Cart(product, user);
			return cartRepository.save(cart);
		} else {
			return null;
		}

	}

	public List<Cart> getCartDeatils() {
		String userName = JwtRequestFilter.CURRENT_USER;
		User user = userRepository.findById(userName).get();
		return cartRepository.findByUser(user);

	}

	public void deleteCartItem(int cartId) {
		cartRepository.deleteById(cartId);

	}
}
