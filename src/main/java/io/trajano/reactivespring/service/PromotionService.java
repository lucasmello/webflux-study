package io.trajano.reactivespring.service;

import io.trajano.reactivespring.api.converters.PromotionModelConverter;
import io.trajano.reactivespring.application.business.PromotionRules;
import io.trajano.reactivespring.application.ports.driver.PromotionPort;
import io.trajano.reactivespring.domain.PromotionModel;
import io.trajano.reactivespring.domain.PromotionResponseModel;
import io.trajano.reactivespring.infrastructure.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class PromotionService implements PromotionPort {

    private final EmployeeRepository employeeRepository;
    private final PromotionRules promotionRules;
    private final PromotionModelConverter promotionModelConverter;

    public PromotionService(@Autowired EmployeeRepository employeeRepository,
                            @Autowired PromotionRules promotionRules,
                            @Autowired PromotionModelConverter promotionModelConverter) {
        this.employeeRepository = employeeRepository;
        this.promotionRules = promotionRules;
        this.promotionModelConverter = promotionModelConverter;
    }

    @Override
    public Mono<PromotionResponseModel> promote(String employeeId, PromotionModel promotionModel) {
        //TODO figure out how to update employee in mongo before converting it.
        return employeeRepository
                .findById(employeeId)
                .flatMap(e -> promotionRules.updateEmployeeInfo(e, promotionModel))
                .doOnNext(employeeRepository::save)
                .flatMap(e -> promotionModelConverter.convert(e, promotionModel));
    }
}
