package com.webcoder.products.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {

	@NotNull(message = "Product name can not be null!")
	@Size(min = 2, message = "Product name must not be less than two characters!")
	private String name;
	@NotNull(message = "Price can not be null!")
	private String price;
	@NotNull(message = "Quantity can not be null!")
	private Integer quantity;
	@NotNull(message = "Category Id can not be null!")
	private String catId;
	@NotNull(message = "Image Url can not be null")
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
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

}
