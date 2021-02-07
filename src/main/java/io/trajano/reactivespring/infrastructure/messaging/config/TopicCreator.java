package io.trajano.reactivespring.infrastructure.messaging.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicCreator {

    @Bean
    public NewTopic employeesTopic() {
        return TopicBuilder.name("employees")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic promotionTopic() {
        return TopicBuilder.name("employee-promotion-v1")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
