package com.globant.pruebas.profileservice.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@ConfigurationProperties(prefix="kafka.profile")
public class Publisher {

	private String topic;
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Autowired
	private KafkaTemplate<Long, String> kafkaTemplate;

	public ListenableFuture<SendResult<Long,String>> sendMessage(Long key, String msg) {
		ListenableFuture<SendResult<Long,String>> result = kafkaTemplate.send(topic,key,msg);
		return result;
	}
	
}
