package com.globant.pruebas.kafka.kafkaconsumer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerTest {

	@Autowired
	Consumer consumer;

	private static final int max_awaiting_time = 10;

	@Test
	public void testConsumer() {
		int i = 0;
		Queue<String> messages = Consumer.getMessagequeue();
		String message = null;
		while(i<max_awaiting_time && message==null) {			
			message = messages.poll();
			try {Thread.sleep(1000);}catch (InterruptedException e) {fail("Interrupted exception: " +e.getMessage());}
		}
		assertNotNull(message);
		System.out.println("Finished testConsumer with message " + message);
	}

}
