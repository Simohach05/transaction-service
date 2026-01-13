package com.greenwealth.transaction_service.infrastructure.messaging;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendInvestmentEvent(String message){
        kafkaTemplate.send("investment-event", message);
        System.out.println("Message was send to kafka:" +message);

    }
}
