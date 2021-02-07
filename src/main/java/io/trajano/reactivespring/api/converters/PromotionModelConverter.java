package io.trajano.reactivespring.api.converters;

import io.trajano.reactivespring.domain.Employee;
import io.trajano.reactivespring.domain.PromotionModel;
import io.trajano.reactivespring.domain.PromotionResponseModel;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PromotionModelConverter {

    public Mono<PromotionResponseModel> convert(Employee employee, PromotionModel promotionModel) {
        PromotionResponseModel promotionResponseModel = new PromotionResponseModel();
        promotionResponseModel.setNewPosition(promotionModel.getNewPosition());
        promotionResponseModel.setNewSalary(promotionModel.getNewSalary());
        promotionResponseModel.setOldPosition(employee.getDepartment());
        promotionResponseModel.setOldSalary(employee.getSalary());
        return Mono.just(promotionResponseModel);
    }

}
