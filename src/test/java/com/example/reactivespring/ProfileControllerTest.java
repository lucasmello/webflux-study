package com.example.reactivespring;

import com.example.reactivespring.api.ProfileController;
import com.example.reactivespring.domain.Profile;
import com.example.reactivespring.service.ProfileService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    public void createValidProfile() {
        Profile profile = new Profile("myId", "mock@testmock.com");


    }

}
