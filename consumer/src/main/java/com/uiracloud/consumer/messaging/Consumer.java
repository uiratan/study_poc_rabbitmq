package com.uiracloud.consumer.messaging;

import com.uiracloud.consumer.model.Message;
import com.uiracloud.consumer.repository.MessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private MessageRepository messageRepository;

//    @RabbitListener(queues = "myQueue")
    @RabbitListener(queues = "myQueue", containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        Message entity = new Message();
        entity.setContent(message);
        messageRepository.save(entity);
    }

}