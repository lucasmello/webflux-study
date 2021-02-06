package com.example.reactivespring;

import com.example.reactivespring.domain.Profile;
import com.example.reactivespring.infrastructure.repository.ProfileRepository;
import com.example.reactivespring.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

import static org.mockito.Mockito.doReturn;

@DataMongoTest
@Import(ProfileService.class)
public class ProfileServiceTest {

    @InjectMocks
    private ProfileService service;
    @Mock
    private ProfileRepository repository;
//
//    public ProfileServiceTest(@Autowired ProfileService profileService,
//                              @Autowired ProfileRepository profileRepository) {
//        this.service = profileService;
//        this.repository = profileRepository;
//    }

    @Test
    void getAllTest() {
        Flux<Profile> saved = repository.saveAll(Flux.just(new Profile(null, "Josh"), new Profile(null, "Matt"), new Profile(null, "Jane")));

        Flux<Profile> composite = service.all().thenMany(saved);

        Predicate<Profile> match = profile -> saved.any(saveItem -> saveItem.equals(profile)).block();

        StepVerifier
                .create(composite)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .verifyComplete();
    }



    @Test
    void getTest() {

        doReturn(Mono.just(new Profile("id", "test_mock!"))).when(repository).findById("123");

        StepVerifier
                .create(service.get("123"))
                .expectNextMatches(p -> p.getEmail().equals("test_mock!"))
                .expectComplete()
                .verify();
    }


}
