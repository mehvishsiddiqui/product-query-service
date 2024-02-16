package com.microservice.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservice.demo.dto.ProductEvent;
import com.microservice.demo.entity.Product;
import com.microservice.demo.repsiotory.ProductQueryRepository;

@Service
public class ProductQueryService {
  
	 @Autowired
	private ProductQueryRepository repository;
	
//	 @GetMapping
	public List<Product> getProducts(){
		return repository.findAll();
	}
	@KafkaListener(topics = "product-event-topic" , groupId = "product-event-group")
public void processProductEvents(ProductEvent productEvent) {
	 Product product =productEvent.getProduct();
	if(productEvent.getEventType().equals("CreateProduct")) {
		repository.save(product);
	} if (productEvent.getEventType().equals("UpdateProduct")) {

		Product existingProduct = repository.findById(product.getId()).get();
	
		
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setDescription(product.getDescription());
		repository.save(existingProduct);
	}
	}
}
