package io.trajano.reactivespring.api;

import io.trajano.reactivespring.application.ports.driver.PromotionPort;
import io.trajano.reactivespring.domain.PromotionModel;
import io.trajano.reactivespring.domain.PromotionResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/promotion", produces = MediaType.APPLICATION_JSON_VALUE)
public class PromotionController {

    @Autowired
    private PromotionPort promotionService;


    @PostMapping("/{employeeId}")
    public Mono<PromotionResponseModel> promote(@PathVariable String employeeId,
                                                @RequestBody PromotionModel promotionModel) {
        return promotionService.promote(employeeId, promotionModel)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

}
