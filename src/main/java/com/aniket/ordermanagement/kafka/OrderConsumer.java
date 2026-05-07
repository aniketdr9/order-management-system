package com.aniket.ordermanagement.kafka;

import com.aniket.ordermanagement.dto.OrderCreateEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    @KafkaListener(
            topics = "order-created-topic",
            groupId= "order-group"
    )

    public void consume(OrderCreateEvent event) {
        System.out.println("Received Order Event: "+ event);
    }
}
