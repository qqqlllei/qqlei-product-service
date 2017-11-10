package com.qqlei.shop.product.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qqlei.shop.product.model.ProductSpecification;

@Mapper
public interface ProductSpecificationMapper {
	
	@Insert("INSERT INTO product_specification(name,value,product_id) VALUES(#{name},#{value},#{productId})")  
	void add(ProductSpecification productSpecification);
	
	@Update("UPDATE product_specification SET name=#{name},value=#{value},product_id=#{productId} WHERE id=#{id}")  
	void update(ProductSpecification productSpecification);
	
	@Delete("DELETE FROM product_specification WHERE id=#{id}")  
	void delete(Long id);
	
	@Select("SELECT * FROM product_specification WHERE id=#{id}")  
	ProductSpecification findById(Long id);
	
}
