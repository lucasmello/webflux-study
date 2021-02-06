package com.example.reactivespring.api;

import com.example.reactivespring.api.exceptions.ResourceNotFoundException;
import com.example.reactivespring.domain.Employee;
import com.example.reactivespring.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    Flux<Employee> getAll() {
        return this.employeeService.all();
    }

    @GetMapping("/{id}")
    Mono<Employee> getById(@PathVariable String id) {
        return this.employeeService.get(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException()));
    }

    @PostMapping
    Mono<ResponseEntity<Employee>> create(@RequestBody Employee employee) {
        return this.employeeService.create(employee)
                .map(p -> ResponseEntity
                        .created(URI.create("/employee/" + employee.getId()))
                        .contentType(mediaType)
                        .build());
    }

    @DeleteMapping("/{id}")
    Mono<Employee> deleteById(@PathVariable String id) {
        return this.employeeService.delete(id);
    }

    @PutMapping("/{id}")
    Mono<Employee> put(@PathVariable String id, @RequestBody Employee employee) {
        return Mono.just(employee)
                .flatMap(p -> this.employeeService.update(id, employee));

    }
}
