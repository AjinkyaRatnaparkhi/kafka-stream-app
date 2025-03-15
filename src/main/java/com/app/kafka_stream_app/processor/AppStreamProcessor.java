package com.app.kafka_stream_app.processor;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.kafka_stream_app.model.Stock;

@Component
public class AppStreamProcessor {

	@Value("${output.kafka.topic}")
	String outputTopic;

	@Value("${input.kafka.topic}")
	String inputTopic;

	@Autowired
	public void streamTopology(StreamsBuilder streamsBuilder , Serde<Stock> stockSerde) {

		KStream<String, Stock> input = streamsBuilder.stream(inputTopic , Consumed.with(Serdes.String(), stockSerde));
		
		KStream<String, Stock> stockStream = input.peek((k ,v) -> System.out.println(" Key " + k + " , value " + v)).map((k,v) -> {
			v.setName(v.getName().toUpperCase());
			return  KeyValue.pair(k,v);
		}).filter((k,v) -> v != null);
		
		stockStream.peek((k ,v) -> System.out.println(" update message Key " + k + " , value " + v)).to(outputTopic,Produced.with(Serdes.String(), stockSerde));

	}
	
}
