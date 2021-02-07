//package com.example.reactivespring.infrastructure.repository;
//
//import com.example.reactivespring.domain.Employee;
//import lombok.extern.log4j.Log4j2;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//
//import java.util.UUID;
//
//@Component
//@org.springframework.context.annotation.Profile("demo")
//public class SampleDataInitializer implements ApplicationListener<ApplicationReadyEvent> {
//
//    private final Logger log = LoggerFactory.getLogger(SampleDataInitializer.class);
//
//    private final EmployeeRepository repository;
//
//    public SampleDataInitializer(EmployeeRepository employeeRepository) {
//        this.repository= employeeRepository;
//    }
//
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        repository
//                .deleteAll()
//                .thenMany(
//                        Flux.just("A", "B", "C", "D")
//                        .map(name -> new Employee(UUID.randomUUID().toString(), name + "@email.com"))
//                        .flatMap(repository::save)
//                ).thenMany(repository.findAll())
//                .subscribe(employee -> log.info("Saving " + employee.toString()));
//    }
//}
