package com.qqlei.shop.product.service;

import com.qqlei.shop.product.model.ProductSpecification;

public interface ProductSpecificationService {
	
	public void add(ProductSpecification productSpecification);
	
	public void update(ProductSpecification productSpecification);
	
	public void delete(Long id);
	
	public ProductSpecification findById(Long id);
	
}
