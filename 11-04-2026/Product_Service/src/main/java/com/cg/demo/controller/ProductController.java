package com.cg.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.demo.model.Product;
import com.cg.demo.service.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@PostMapping("/")
	public void addProduct(@RequestBody Product product) {
		productServiceImpl.addProduct(product);
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Integer id) {
		return productServiceImpl.getProductById(id);
	}
}
