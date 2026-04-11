package com.cg.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.exception.ProductNotFoundException;
import com.cg.demo.model.Product;
import com.cg.demo.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		productRepo.save(product);

	}

	@Override
	public Product getProductById(Integer id) throws ProductNotFoundException {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
		return product;
	}

}
