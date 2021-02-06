package com.example.reactivespring.infrastructure.messaging.senders;

import com.example.reactivespring.domain.Employee;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.util.UUID;

@Component
public class EmployeeCreatedEventSender {

    private final Logger logger = LoggerFactory.getLogger(EmployeeCreatedEventSender.class);
    private final KafkaSender<String, Employee> kafkaSender;
    private final String topic;

    public EmployeeCreatedEventSender(KafkaSender<String, Employee> kafkaSender,
                                      @Value("${kafka.employees.topic}") String topic) {
        this.kafkaSender = kafkaSender;
        this.topic = topic;
    }

    public Mono<Employee> send(Employee employee) {
        var producerRecord = new ProducerRecord<String, Employee>(topic, employee);
        var senderRecord = SenderRecord.create(producerRecord, UUID.randomUUID().toString());

        logger.info("Sending message '{}' to topic '{}'", employee, topic);

        return kafkaSender.send(Mono.just(senderRecord))
                .next()
                .thenReturn(employee);
    }
}
