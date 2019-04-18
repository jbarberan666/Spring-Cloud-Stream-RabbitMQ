package com.example.SpringCloudStreamRabbitMQ.controller.producer;

import com.example.SpringCloudStreamRabbitMQ.entity.SampleMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@EnableBinding(Source.class)
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerMessageController {

    private final Source source;

    @PostMapping("/api/messages")
    public String newMessage(@RequestBody SampleMessage message ) {
        log.info("Publishing new message: {}", message);
        source.output().send(MessageBuilder.withPayload(message).build());

        return "Published new message: " + message.getSenderFirstName() + " " + message.getSenderLastName()+ " " +message.getContent();
    }
}
