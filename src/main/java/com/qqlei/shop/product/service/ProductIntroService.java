package com.qqlei.shop.product.service;

import com.qqlei.shop.product.model.ProductIntro;

public interface ProductIntroService {
	
	public void add(ProductIntro productIntro);
	
	public void update(ProductIntro productIntro);
	
	public void delete(Long id);
	
	public ProductIntro findById(Long id);
	
}
