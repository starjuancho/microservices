package com.globant.pruebas.profileservice.message;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestPublisher {

	private static final String MESSAGE = "Message from test sender ";

	@Autowired 
	Publisher publisher;
	
//	@Test
	public void testSendMessage() {
		System.out.format("Starting testSendMessage%n");
		Long key = System.currentTimeMillis();
		ListenableFuture<SendResult<Long,String>> result = publisher.sendMessage(key,MESSAGE);
		assertNotNull(result);
		
		System.out.format("Finished testInsertUserNull%n");
	}
}
