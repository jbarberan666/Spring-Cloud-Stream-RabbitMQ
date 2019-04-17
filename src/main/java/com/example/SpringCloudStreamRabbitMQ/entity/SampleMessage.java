package com.example.SpringCloudStreamRabbitMQ.entity;

import lombok.*;

@Data
@RequiredArgsConstructor
@ToString
public class Message {

    private String senderLastName;
    private String senderFirstName;
    private String content;
}
