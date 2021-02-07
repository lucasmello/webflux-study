package io.trajano.reactivespring.application.ports.driver;

import io.trajano.reactivespring.domain.PromotionModel;
import io.trajano.reactivespring.domain.PromotionResponseModel;
import reactor.core.publisher.Mono;

public interface PromotionPort {

    Mono<PromotionResponseModel> promote(String employeeId, PromotionModel promotionModel);

}
