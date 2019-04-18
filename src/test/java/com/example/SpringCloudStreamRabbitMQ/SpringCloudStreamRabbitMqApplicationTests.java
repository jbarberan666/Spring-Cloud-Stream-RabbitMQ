package com.example.SpringCloudStreamRabbitMQ;

import com.example.SpringCloudStreamRabbitMQ.entity.SampleMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringCloudStreamRabbitMqApplicationTests {

	@Autowired
	private Source inBoundchannels;


	@Autowired
	private MessageCollector collector;





	@Test
	public void testMessages() {

		ObjectMapper mapper = new ObjectMapper();

		SampleMessage sampleMessageSend=new SampleMessage();
		sampleMessageSend.setSenderFirstName("Julien");
		sampleMessageSend.setSenderLastName("Barberan");
		sampleMessageSend.setContent("a message from the the beyond");

		this.inBoundchannels.output().send(MessageBuilder.withPayload(sampleMessageSend).build());

		Message received = collector.forChannel(inBoundchannels.output()).poll();
		assertNotNull(received.getPayload());
		log.info("Sample Message received {}",received.getPayload());

		//JSON from String to Object
		try {
			SampleMessage sampleMessageReceived = mapper.readValue(received.getPayload().toString(), SampleMessage.class);
			assertEquals(sampleMessageSend,sampleMessageReceived);
		} catch (IOException e) {
			e.printStackTrace();
		}


		//verify(this.toUpperCaseProcessor, times(1)).transform(anyString());
	}
}
