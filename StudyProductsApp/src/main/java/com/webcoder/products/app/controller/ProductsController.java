package com.webcoder.products.app.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webcoder.products.app.model.Product;
import com.webcoder.products.app.model.ProductResponseModel;
import com.webcoder.products.app.service.ProductService;
import com.webcoder.products.app.shared.ProductDto;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	Environment env;
	@Autowired
	ProductService productService;

	@GetMapping("/status/check")
	public String checkStatus() {
		return "Products Service is working on! " + env.getProperty("local.server.port") + " with token "
				+ env.getProperty("token.secret");
	}

	@GetMapping("/{catId}")
	public ResponseEntity<List<ProductResponseModel>> getProductsByCategoryId(@PathVariable String catId) {

		List<ProductDto> productDto = productService.findProductByCatId(catId);
		// ProductResponseModel productResponseModel = new ModelMapper().map(productDto,
		// ProductResponseModel.class);
		ModelMapper modelMapper = new ModelMapper();
		List<ProductResponseModel> productResponseModel = Arrays
				.asList(modelMapper.map(productDto, ProductResponseModel[].class));
		return ResponseEntity.status(HttpStatus.OK).body(productResponseModel);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ProductResponseModel> createUser(@Valid @RequestBody Product productRequest) {

		ModelMapper modelMapper = new ModelMapper();
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ProductDto createdProduct = productService.createProduct(productDto);
		ProductResponseModel returnValue = modelMapper.map(createdProduct, ProductResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

}
