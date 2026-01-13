package com.greenwealth.transaction_service.infrastructure.messaging;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "investment-events", groupId = "notification-group")
    public void listenToInvestmentEvents(String message){
        System.out.println("[Consumer] The message received from Kafka: "+ message);
        System.out.println("Simulation : send client notification...");
    }
}
