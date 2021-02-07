package io.trajano.reactivespring.application.business;

import io.trajano.reactivespring.domain.Employee;
import io.trajano.reactivespring.domain.PromotionModel;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PromotionRules {

    public Mono<Employee> updateEmployeeInfo(Employee employee, PromotionModel promotionModel) {
        employee.setSalary(promotionModel.getNewSalary());
        employee.setDepartment(promotionModel.getNewPosition());
        return Mono.just(employee);
    }

}
