package com.osa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.osa.configuration.JwtRequestFilter;
import com.osa.dao.CartRepository;
import com.osa.dao.ProductRepository;
import com.osa.dao.UserRepository;
import com.osa.entity.Cart;
import com.osa.entity.Product;
import com.osa.entity.User;
import com.osa.exception.ProductAlreadyExists;
import com.osa.exception.ProductIdNotFound;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartRepository cartRepository;
	
	public Product addNewProduct(Product product) throws ProductAlreadyExists{
//		Optional<Product> p=productRepository.findById(product.getProductId());
//		if(p.isPresent()) {
//			throw new ProductAlreadyExists("Product already exists");
//		}
		return productRepository.save(product);
	}
	
	public List<Product> getAllProducts(int  pageNumber,String searchKey){
		Pageable pageable=PageRequest.of(pageNumber, 12);
		if(searchKey.equals("")) {
		return (List<Product>)productRepository.findAll(pageable);
		}
		else {
			return productRepository.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
		}
		
	}

	public void deleteProductDetails(int productId) throws ProductIdNotFound{
		if(productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			
		}
		else {
			throw new ProductIdNotFound("product Id doesn't exits");
		}
		
	}
	
	
	public Product getProductDetailsById(int productId) {
		return productRepository.findById(productId).get();
		
	}
	
	public List<Product> getProductDetails(boolean isSingleProductCheckOut ,int productId) {
		if(isSingleProductCheckOut && productId!=0) {
//			we are going to buy a single product
			List<Product> list=new ArrayList<>();
			Product product=productRepository.findById(productId).get();
			list.add(product);
			return list;
		}else {
//			we are going to checkout entire cart
			String userName=JwtRequestFilter.CURRENT_USER;
			User user=userRepository.findById(userName).get();
			List<Cart> carts=cartRepository.findByUser(user);
			return carts.stream().map(x->x.getProduct()).collect(Collectors.toList());
			
		}
	}
	
	
		
		
	
}
