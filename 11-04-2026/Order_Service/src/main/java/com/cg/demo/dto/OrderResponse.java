package com.cg.demo.dto;

import jakarta.validation.constraints.Min;

public class OrderResponse {

	private Integer orderId;
	private String userName;
	private String productName;
	@Min(value = 1, message = "Quantity must be at least 1")
	private Integer quantity;
	private Double totalPrice;

	public OrderResponse() {
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userName=" + userName + ", productName=" + productName + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + "]";
	}

}
