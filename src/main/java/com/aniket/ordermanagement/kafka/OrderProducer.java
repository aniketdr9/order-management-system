package com.aniket.ordermanagement.kafka;

import com.aniket.ordermanagement.dto.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, OrderCreateEvent> kafkaTemplate;

    private static final String TOPIC = "order-created-topic";

    public void sendOrderCreatedEvent(OrderCreateEvent orderCreateEvent) {
        kafkaTemplate.send(TOPIC, orderCreateEvent);
        System.out.println("Order Created Event sent to topic: " + orderCreateEvent);
    }
}
