package com.example.SpringCloudStreamRabbitMQ.entity;

import lombok.*;

@Data
@RequiredArgsConstructor
@ToString
public class SampleMessage {

    private String senderLastName;
    private String senderFirstName;
    private String content;
}
