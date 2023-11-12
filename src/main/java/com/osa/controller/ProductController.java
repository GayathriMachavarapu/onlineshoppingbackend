package com.osa.controller;



import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.osa.entity.ImageModel;
import com.osa.entity.Product;
import com.osa.exception.ProductAlreadyExists;
import com.osa.exception.ProductIdNotFound;
import com.osa.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping(value="/addNewProduct",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product addNewProduct(@RequestPart("product") Product product,@RequestPart("imageFile") MultipartFile[] file) throws ProductAlreadyExists{
		//return new ResponseEntity<Product>(productService.addNewProduct(product),HttpStatus.OK);
		try {
			Set<ImageModel> images=uploadImage(file);
			product.setProductImages(images);
			return productService.addNewProduct(product);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
			
		}
	}
	
	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<ImageModel> imageModels=new HashSet<>();
		
		for(MultipartFile file:multipartFiles) {
			ImageModel imageModel=new ImageModel(
					file.getOriginalFilename(),
					file.getContentType(),
					file.getBytes()
					);
			imageModels.add(imageModel);
			
		}
		return imageModels;
	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "") String searchKey){
		List<Product> result= productService.getAllProducts(pageNumber,searchKey);
		System.out.println("result size is: "+result.size());
		return result;
	}
	
	@DeleteMapping("/deleteProductDetails/{productId}")
	public void deleteProductDetails(@PathVariable("productId") int ProductId) throws ProductIdNotFound{
		productService.deleteProductDetails(ProductId);
	}
	
	@GetMapping("/getProductDetailsById/{productId}")
	public Product getProductDetailsById(@PathVariable("productId") int productId) {
		return productService.getProductDetailsById(productId);
		
	}
	@PreAuthorize("hasRole('Admin')")
	@PutMapping("/updateProductDetails")
	public Product updateProductDetails(@RequestBody Product product) {
		return null;
	}
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getProductDetails/{isSingleProductCheckout}/{productId}")
	public List<Product> getProductDetails(@PathVariable(name="isSingleProductCheckout") boolean isSingleProductCheckout,@PathVariable(name="productId") int productId) {
	  return productService.getProductDetails(isSingleProductCheckout, productId);
	}
	
	

}
