package com.example.reactivespring.infrastructure.kafka;

import com.example.reactivespring.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private KafkaTemplate<String, Profile> kafkaTemplate;

    public KafkaConsumer(@Autowired KafkaTemplate<String, Profile> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "profiles", groupId = "my_group", containerGroup = "kafkaListenerContainerFactory")
    public void consume(Profile profile) {
        if (profile.getEmail().contains("gmail.com")) {
            kafkaTemplate.send("profiles-with-gmail", profile);
        }
    }

}
