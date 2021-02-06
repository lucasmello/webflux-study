package com.example.reactivespring.service;

import com.example.reactivespring.domain.Profile;
import org.springframework.context.ApplicationEvent;

public class ProfileCreatedEvent extends ApplicationEvent {
    public ProfileCreatedEvent(Profile profile) {
        super(profile);
    }
}
