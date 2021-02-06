package com.example.reactivespring.infrastructure.repository;

import com.example.reactivespring.domain.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {
}
