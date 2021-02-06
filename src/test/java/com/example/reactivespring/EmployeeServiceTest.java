package com.example.reactivespring;

import com.example.reactivespring.domain.Employee;
import com.example.reactivespring.infrastructure.repository.EmployeeRepository;
import com.example.reactivespring.service.EmployeeService;
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
@Import(EmployeeService.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService service;
    @Mock
    private EmployeeRepository repository;
//
//    public ProfileServiceTest(@Autowired ProfileService profileService,
//                              @Autowired ProfileRepository profileRepository) {
//        this.service = profileService;
//        this.repository = profileRepository;
//    }

    @Test
    void getAllTest() {
//        Flux<Employee> saved = repository.saveAll(Flux.just(new Employee(null, "Josh"), new Employee(null, "Matt"), new Employee(null, "Jane")));
//
//        Flux<Employee> composite = service.all().thenMany(saved);
//
//        Predicate<Employee> match = employee -> saved.any(saveItem -> saveItem.equals(employee)).block();
//
//        StepVerifier
//                .create(composite)
//                .expectNextMatches(match)
//                .expectNextMatches(match)
//                .expectNextMatches(match)
//                .verifyComplete();
    }



    @Test
    void getTest() {

//        doReturn(Mono.just(new Employee("id", "test_mock!"))).when(repository).findById("123");
//
//        StepVerifier
//                .create(service.get("123"))
//                .expectNextMatches(p -> p.getEmail().equals("test_mock!"))
//                .expectComplete()
//                .verify();
    }


}
