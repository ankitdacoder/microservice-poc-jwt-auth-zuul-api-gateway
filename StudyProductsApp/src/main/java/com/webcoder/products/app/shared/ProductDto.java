package com.webcoder.products.app.shared;

import java.io.Serializable;

public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6105036188412753152L;
	
	private String productId;
	private String name;
	private String price;
	private Integer quantity;
	private String catId;
	private String imageUrl;
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	

}
