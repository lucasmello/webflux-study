package com.example.reactivespring.infrastructure.messaging;

import com.example.reactivespring.domain.Employee;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "profiles", groupId = "my_group", containerGroup = "kafkaListenerContainerFactory")
    public void consume(Employee employee) {

    }



}
