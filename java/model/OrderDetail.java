package model;

import org.springframework.stereotype.Component;

@Component
public class OrderDetail {
	private Integer orderID;
	private Integer productDetailID;
	private String color;
	private String size;
	private Integer quantity;
	private Integer price;
	
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Integer getProductDetailID() {
		return productDetailID;
	}
	public void setProductDetailID(Integer productDetailID) {
		this.productDetailID = productDetailID;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
