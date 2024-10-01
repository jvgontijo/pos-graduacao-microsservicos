package com.gontijo.user.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName = "userQueue";

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(queueName, true);
    }
}
