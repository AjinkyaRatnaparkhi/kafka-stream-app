package com.app.kafka_stream_app.model;

import java.time.ZonedDateTime;

public class Stock {

	  	private String name;
	    private ZonedDateTime lastRefreshed;
	    private Long currentValue;
	    private Long volume;
	    private String index;
	    
	    public Stock() {
	    	
	    }
	    
		@Override
		public String toString() {
			return "Stock [name=" + name + ", lastRefreshed=" + lastRefreshed + ", currentValue=" + currentValue
					+ ", volume=" + volume + ", index=" + index + "]";
		}
		public Stock(String name, ZonedDateTime lastRefreshed, Long currentValue, Long volume, String index) {
			//super();
			this.name = name;
			this.lastRefreshed = lastRefreshed;
			this.currentValue = currentValue;
			this.volume = volume;
			this.index = index;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public ZonedDateTime getLastRefreshed() {
			return lastRefreshed;
		}
		public void setLastRefreshed(ZonedDateTime lastRefreshed) {
			this.lastRefreshed = lastRefreshed;
		}
		public Long getCurrentValue() {
			return currentValue;
		}
		public void setCurrentValue(Long currentValue) {
			this.currentValue = currentValue;
		}
		public Long getVolume() {
			return volume;
		}
		public void setVolume(Long volume) {
			this.volume = volume;
		}
		public String getIndex() {
			return index;
		}
		public void setIndex(String index) {
			this.index = index;
		}
	
	    
	    
}
