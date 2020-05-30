package com.webcoder.products.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.webcoder.products.app.data.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Long>  {

	List<ProductEntity> findBycatId(String catId);
}
