package com.globant.pruebas.kafka.kafkaconsumer;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "kafka.profile")
@Component
public class Consumer {

	private static final Queue<String> messageQueue= new LinkedList<>();
	
	@KafkaListener(topics="${kafka.profile.topic}")
	public void listen(String message) {
		System.out.println("Received Messasge: " + message);
		messageQueue.add(message);
	}

	public static Queue<String> getMessagequeue() {
		return messageQueue;
	}
}
