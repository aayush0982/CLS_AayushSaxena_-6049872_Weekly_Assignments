package com.cg.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cg.demo.dto.OrderResponse;
import com.cg.demo.dto.Product;
import com.cg.demo.dto.User;
import com.cg.demo.exception.ProductNotFoundException;
import com.cg.demo.exception.UserNotFoundException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderServiceImpl implements OrderServiceInterface {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@CircuitBreaker(name = "orderService", fallbackMethod = "fallbackMethod")
	public OrderResponse placeOrder(Integer userId, int productId, int quantity) {

		User user;
		Product product;

		try {
			user = restTemplate.getForObject("http://UserService1/users/" + userId, User.class);
		} catch (org.springframework.web.client.HttpClientErrorException.NotFound ex) {
			throw new UserNotFoundException("User not found with id: " + userId);
		} catch (Exception ex) {
			throw new RuntimeException("User service is down", ex);
		}

		try {
			product = restTemplate.getForObject("http://ProductService/products/" + productId, Product.class);
		} catch (org.springframework.web.client.HttpClientErrorException.NotFound ex) {
			throw new ProductNotFoundException("Product not found with id: " + productId);
		} catch (Exception ex) {
			throw new RuntimeException("Product service is down", ex);
		}

		if (user == null) {
			throw new UserNotFoundException("User service returned null for id: " + userId);
		}

		if (product == null) {
			throw new ProductNotFoundException("Product service returned null for id: " + productId);
		}

		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderId(userId * 1000 + productId);
		orderResponse.setUserName(user.getUserName());
		orderResponse.setProductName(product.getProductName());
		orderResponse.setQuantity(quantity);
		orderResponse.setTotalPrice(product.getProductPrice() * quantity);

		return orderResponse;
	}

	public OrderResponse fallbackMethod(Integer userId, int productId, int quantity, Exception ex) {
		System.out.println(" FALLBACK TRIGGERED ");
		OrderResponse response = new OrderResponse();
		response.setOrderId(-1);
		response.setUserName("Service Unavailable");
		response.setProductName("Service Unavailable");
		response.setQuantity(quantity);
		response.setTotalPrice(0.0);

		return response;
	}
}