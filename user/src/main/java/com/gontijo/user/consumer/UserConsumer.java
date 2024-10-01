package com.gontijo.user.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    @RabbitListener(queues = "userQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
