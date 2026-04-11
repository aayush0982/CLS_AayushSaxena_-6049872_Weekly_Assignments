package com.cg.demo.service;

import com.cg.demo.dto.OrderResponse;

public interface OrderServiceInterface {
	public OrderResponse placeOrder(Integer userId, int productId, int quantity);
}
