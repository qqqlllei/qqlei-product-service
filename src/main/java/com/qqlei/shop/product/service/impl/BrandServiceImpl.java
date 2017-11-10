package com.qqlei.shop.product.service.impl;

import com.qqlei.shop.product.rabbitmq.RabbitMQSender;
import com.qqlei.shop.product.rabbitmq.RabbitQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqlei.shop.product.mapper.BrandMapper;
import com.qqlei.shop.product.model.Brand;
import com.qqlei.shop.product.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandMapper brandMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(Brand brand) {
		brandMapper.add(brand);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"brand\", \"id\": " + brand.getId() + "}");
	}

	public void update(Brand brand) {
		brandMapper.update(brand);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"brand\", \"id\": " + brand.getId() + "}");
	}

	public void delete(Long id) {
		brandMapper.delete(id);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"brand\", \"id\": " + id + "}");
	}

	public Brand findById(Long id) {
		return brandMapper.findById(id);
	}

}
