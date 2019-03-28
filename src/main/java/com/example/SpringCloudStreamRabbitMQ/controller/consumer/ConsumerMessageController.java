package com.example.SpringCloudStreamRabbitMQ.controller.consumer;

import com.example.SpringCloudStreamRabbitMQ.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@EnableBinding(Sink.class)
@RestController
@Slf4j
public class ConsumerMessageController {
    private final SseEmitter sseEmitter = new SseEmitter(0L);

    @StreamListener(Sink.INPUT)
    public void onMessagePublished(Message m) throws IOException {
        log.info("Received message: {}", m);
        sseEmitter.send(m);
    }

    @GetMapping(value = "/")
    public SseEmitter streamingMessage() {
        return sseEmitter;
    }
}
