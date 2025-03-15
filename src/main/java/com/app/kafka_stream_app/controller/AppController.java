package com.app.kafka_stream_app.controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.kafka_stream_app.config.AppKafkaProducer;
import com.app.kafka_stream_app.model.Stock;

@RestController
public class AppController {

	

	@PostMapping("/api/publishStock")
	public String publishStock(@RequestBody Stock stock) {
	
		try {
			
			AppKafkaProducer appKafkaProducer = new AppKafkaProducer();
			appKafkaProducer.getProducer().send(new ProducerRecord<String, Stock>("stock-in-topic",stock.getName() ,stock));
			appKafkaProducer.getProducer().flush();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "stock published ";
		
	}
		
	@GetMapping("/api/publishMockData")
	public String publishMockData() {
		
		AppKafkaProducer appKafkaProducer = new AppKafkaProducer();
		appKafkaProducer.publishData();
		return "successfully published the mock data !";
		
	}
	
}
