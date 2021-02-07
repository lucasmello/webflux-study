package io.trajano.reactivespring.service;

import io.trajano.reactivespring.domain.Employee;
import io.trajano.reactivespring.infrastructure.messaging.senders.EmployeeCreatedEventSender;
import io.trajano.reactivespring.infrastructure.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeCreatedEventSender employeeCreatedEventSender;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void sendMessage(Employee employee) {
        System.out.printf("Posting employee: %s%n", employee);
        employeeCreatedEventSender.send(employee).subscribe();
    }

    public Flux<Employee> all() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> get(String id) {
        return employeeRepository.findById(id);
    }

    public Mono<Employee> delete(String id) {
        return employeeRepository
                .findById(id)
                .flatMap(p -> employeeRepository.deleteById(id).thenReturn(p));
    }

    public Mono<Employee> create(Employee employee) {
        return employeeRepository
                .save(employee)
                .doOnSuccess(this::sendMessage)
                .doOnError(Throwable::printStackTrace);
    }

    public Mono<Employee> update(String id, Employee employee) {
        return this.employeeRepository
                .findById(id)
                .map(p-> employee)
                .flatMap(employeeRepository::save);
    }

}
