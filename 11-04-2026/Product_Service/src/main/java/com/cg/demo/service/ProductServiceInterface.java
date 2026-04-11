package com.cg.demo.service;

import com.cg.demo.exception.ProductNotFoundException;
import com.cg.demo.model.Product;

public interface ProductServiceInterface {
	public void addProduct(Product product);

	public Product getProductById(Integer id) throws ProductNotFoundException;

}
