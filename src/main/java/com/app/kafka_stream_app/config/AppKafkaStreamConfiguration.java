package com.app.kafka_stream_app.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.app.kafka_stream_app.model.Stock;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class AppKafkaStreamConfiguration {

	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	KafkaStreamsConfiguration kafkaStreamConfiguration() {
		   Map<String, Object> props = new HashMap<>();
	        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-app");
	        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
	        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, stockSerde().getClass().getName());

	        return new KafkaStreamsConfiguration(props);
		
	}
	
	@Bean
	Serde<Stock> stockSerde() {
		
		return Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(Stock.class));
		
	}
	
	

}
