package com.webcoder.products.app.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webcoder.products.app.data.ProductEntity;
import com.webcoder.products.app.repository.ProductRepository;
import com.webcoder.products.app.service.ProductService;
import com.webcoder.products.app.shared.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public ProductDto createProduct(ProductDto productDto) {

		productDto.setProductId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
		productRepository.save(productEntity);
		ProductDto returnValue = modelMapper.map(productEntity, ProductDto.class);
		return returnValue;
	}

	@Override
	public List<ProductDto> findProductByCatId(String catId) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<ProductEntity> productEntity = productRepository.findBycatId(catId);
		//ProductDto returnValue = modelMapper.map(productEntity, ProductDto.class);
		return Arrays.asList(modelMapper.map(productEntity, ProductDto[].class));
		//return postDtoList;
	}

}
