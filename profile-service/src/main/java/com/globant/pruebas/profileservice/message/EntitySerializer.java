package com.globant.pruebas.profileservice.message;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.pruebas.profileservice.model.Entity;

@Component
public class EntitySerializer implements  Serializer<Entity> {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private StringSerializer stringSerializer;
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public byte[] serialize(String topic, Entity data) {
			String jSONString = null;
			try {
				jSONString = objectMapper.writeValueAsString(data);
			} catch (JsonProcessingException e) {				
				throw new SerializationException(e.getMessage());
			}
			return stringSerializer.serialize(topic, jSONString);
	}

	@Override
	public void close() {
	}

}
