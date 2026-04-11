package com.cg.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.demo.dto.OrderResponse;
import com.cg.demo.service.OrderServiceImpl;

import jakarta.validation.constraints.Min;

@RestController
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;

	@PostMapping("/orders")
	public OrderResponse placeOrder(@RequestParam Integer userId, @RequestParam Integer productId,
			@Min(value = 1, message = "Quantity should be > 0") @RequestParam int quantity) throws Exception {
		return orderService.placeOrder(userId, productId, quantity);
	}

}
