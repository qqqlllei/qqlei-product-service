package com.qqlei.shop.product.service.impl;

import com.qqlei.shop.product.rabbitmq.RabbitMQSender;
import com.qqlei.shop.product.rabbitmq.RabbitQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqlei.shop.product.mapper.ProductIntroMapper;
import com.qqlei.shop.product.model.ProductIntro;
import com.qqlei.shop.product.service.ProductIntroService;

@Service
public class ProductIntroServiceImpl implements ProductIntroService {

	@Autowired
	private ProductIntroMapper productIntroMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(ProductIntro productIntro) {
		productIntroMapper.add(productIntro);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"product_intro\", \"id\": " + productIntro.getId() + "}");
	}

	public void update(ProductIntro productIntro) {
		productIntroMapper.update(productIntro);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"product_intro\", \"id\": " + productIntro.getId() + ", \"product_id\": " + productIntro.getProductId() + "}");
	}

	public void delete(Long id) {
		productIntroMapper.delete(id);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"product_intro\", \"id\": " + id + "}");
	}

	public ProductIntro findById(Long id) {
		return productIntroMapper.findById(id);

	}

}
