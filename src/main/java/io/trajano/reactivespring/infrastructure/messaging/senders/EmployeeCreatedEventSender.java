package io.trajano.reactivespring.infrastructure.messaging.senders;

import io.trajano.reactivespring.common.JsonParser;
import io.trajano.reactivespring.domain.Employee;
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
    private final KafkaSender<String, String> kafkaSender;
    private final String topic;
    private final JsonParser jsonParser;

    public EmployeeCreatedEventSender(KafkaSender<String, String> kafkaSender,
                                      @Value("${kafka.employees.topic}") String topic,
                                      JsonParser jsonParser) {
        this.kafkaSender = kafkaSender;
        this.topic = topic;
        this.jsonParser = jsonParser;
    }

    public Mono<Employee> send(Employee employee) {
        String body = jsonParser.parse(employee);
        var producerRecord = new ProducerRecord<String, String>(topic, body);
        var senderRecord = SenderRecord.create(producerRecord, UUID.randomUUID().toString());

        logger.info("Sending message '{}' to topic '{}'", employee, topic);

        return kafkaSender.send(Mono.just(senderRecord))
                .doOnError(Throwable::printStackTrace)
                .doOnNext(l -> logger.info("Generated ID " + l.correlationMetadata()))
                .next()
                .thenReturn(employee);
    }
}
