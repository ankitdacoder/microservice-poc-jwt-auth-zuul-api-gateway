package com.webcoder.products.app.service;

import java.util.List;

import com.webcoder.products.app.shared.ProductDto;

public interface ProductService {
 	ProductDto createProduct(ProductDto productDto);
 	List<ProductDto> findProductByCatId(String catId);
}
