package com.qqlei.shop.product.service.impl;

import com.qqlei.shop.product.rabbitmq.RabbitMQSender;
import com.qqlei.shop.product.rabbitmq.RabbitQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqlei.shop.product.mapper.ProductMapper;
import com.qqlei.shop.product.model.Product;
import com.qqlei.shop.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(Product product) {
		productMapper.add(product);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"product\", \"id\": " + product.getId() + "}");
	}

	public void update(Product product) {
		productMapper.update(product);
		System.out.println("================================商品服务更新操作【"+product.getId()+"】========================================");
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"product\", \"id\": " + product.getId() + "}");
	}

	public void delete(Long id) {
		productMapper.delete(id);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"product\", \"id\": " + id + "}");
	}

	public Product findById(Long id) {
		return productMapper.findById(id);
	}

}
