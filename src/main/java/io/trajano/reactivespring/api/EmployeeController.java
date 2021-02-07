package io.trajano.reactivespring.api;

import io.trajano.reactivespring.api.exceptions.ResourceNotFoundException;
import io.trajano.reactivespring.domain.Employee;
import io.trajano.reactivespring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
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
    Mono<Employee> create(@RequestBody Employee employee) {
        return this.employeeService.create(employee);
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
