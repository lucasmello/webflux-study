package io.trajano.reactivespring.infrastructure.messaging;

import io.trajano.reactivespring.domain.Employee;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "profiles", groupId = "my_group", containerGroup = "kafkaListenerContainerFactory")
    public void consume(Employee employee) {

    }



}
