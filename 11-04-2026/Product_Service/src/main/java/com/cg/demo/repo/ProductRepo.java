package com.cg.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
