package com.app.kafka_stream_app.config;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.app.kafka_stream_app.model.Stock;

public class AppKafkaProducer {

	  private KafkaProducer<String, Stock> producer;
	    public AppKafkaProducer() {
	        Properties producerProps = new Properties();
	        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

	        producer = new KafkaProducer<>(producerProps);
	    }

	    
	    
	    public KafkaProducer<String, Stock> getProducer() {
			return producer;
		}



		public void setProducer(KafkaProducer<String, Stock> producer) {
			this.producer = producer;
		}



		public void publishData() {
	        Map<String, Stock> data = getMockData();
	            while (true) {
	                data.forEach((key, value) -> {
	                    value.setCurrentValue(value.getCurrentValue() + (long) (Math.random() * (30 + 30)) - 30);
	                    value.setVolume(value.getVolume() + (long) (Math.random() * (200 - 20)) + 20);
	                    value.setIndex("nifty");
	                    value.setLastRefreshed(ZonedDateTime.now());
	                    producer.send(new ProducerRecord<>("stock-in-topic", key, value));
	                    producer.flush();
	                    try {
	                        Thread.sleep(2000L);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    value.setCurrentValue(value.getCurrentValue() + (long) (Math.random() * (30 - 10)) + 10);
	                    value.setVolume(value.getVolume() + (long) (Math.random() * (200 - 20)) + 20);
	                    value.setIndex("sensex");
	                    value.setLastRefreshed(ZonedDateTime.now());
	                    producer.send(new ProducerRecord<>("stock-in-topic", key, value));
	                    producer.flush();


	                });
	               try {
	            	   Thread.sleep(5000L);
	               } catch (InterruptedException e) {
	                   e.printStackTrace();
	               }
	            }
	    }

	    private Map<String, Stock> getMockData() {
	        Map<String, Stock> stocks = new HashMap<>();

	        List<String> symbols = Arrays.asList("MSFT", "AMZN", "UBER", "ABNB", "NVDA", "NUE", "IT", "EXR", "GOOGL", "AAPL");
	        List<String> names = Arrays.asList("Microsoft", "Amazon", "Uber", "AirBNB", "NVidia Corp", "Nucor Corp.", "Gartner Inc.", "Extra Space Storage Inc.", "Google", "Apple");

	        for(int i=0;i<symbols.size();i++) {
	            Stock stock = new Stock();
	            stock.setLastRefreshed(ZonedDateTime.now());
	            stock.setVolume(50 + (long) (Math.random() * (500 - 50)));
	            stock.setName(names.get(i));
	            stock.setCurrentValue(300 + (long) (Math.random() * (10000 - 300)));
	            stocks.put(symbols.get(i), stock);
	        }

	        return stocks;
	    }
}
