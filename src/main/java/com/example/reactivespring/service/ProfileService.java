package com.example.reactivespring.service;

import com.example.reactivespring.domain.Profile;
import com.example.reactivespring.infrastructure.repository.ProfileRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class ProfileService {

    @Autowired
    private KafkaTemplate<String, Profile> profileKafkaTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ProfileRepository profileRepository;

    public ProfileService(ApplicationEventPublisher applicationEventPublisher, ProfileRepository profileRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.profileRepository = profileRepository;
    }

    public void sendMessage(Profile profile) {
        System.out.printf("Posting profile: %s%n", profile);
        profileKafkaTemplate.send("profiles", profile);
    }

    public Flux<Profile> all() {
        return profileRepository.findAll();
    }

    public Mono<Profile> get(String id) {
        return profileRepository.findById(id);
    }

    public Mono<Profile> delete(String id) {
        return profileRepository
                .findById(id)
                .flatMap(p -> profileRepository.deleteById(id).thenReturn(p));
    }

    public Mono<Profile> create(String email) {
        return profileRepository
                .save(new Profile(null, email))
                .doOnSuccess(profile -> applicationEventPublisher.publishEvent(new ProfileCreatedEvent(profile)))
                .doOnSuccess(this::sendMessage)
                .doOnError(RuntimeException::new);
    }

    public Mono<Profile> update(String id, String email) {
        return this.profileRepository
                .findById(id)
                .map(p-> new Profile(id, email))
                .flatMap(profileRepository::save);
    }
}
