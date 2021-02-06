package com.example.reactivespring.infrastructure.repository;

import com.example.reactivespring.domain.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}
