package io.trajano.reactivespring.infrastructure.repository;

import io.trajano.reactivespring.domain.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}
