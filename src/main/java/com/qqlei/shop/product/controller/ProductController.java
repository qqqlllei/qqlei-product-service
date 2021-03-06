package com.qqlei.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qqlei.shop.product.model.Product;
import com.qqlei.shop.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Value("${my.profile.active}")
	private String myProfileActive;
	
	@RequestMapping("/add") 
	@ResponseBody
	public String add(Product product) {
		try {
			productService.add(product);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/update") 
	@ResponseBody
	public String update(Product product) {
		try {
			productService.update(product); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/delete") 
	@ResponseBody
	public String delete(Long id) {
		try {
			productService.delete(id); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/findById") 
	@ResponseBody
	public Product findById(Long id){
		try {
			Product  result =  productService.findById(id);
			result.setName(result.getName()+"my.profile.active:"+myProfileActive);
			System.out.println("findById"+"["+ JSONObject.toJSON(result).toString()+"]");
			return result;
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new Product();
	}
	
}
