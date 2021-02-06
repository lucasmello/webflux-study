package com.example.reactivespring;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

public class OtherTests {

    @Test
    public void fluxTest() {

        Flux<Integer> flux = Flux.range(1, 3)
                .map(i -> i * 2)
                .log();

        Flux.just("1", "2")
                .flatMap(this::strings)
                .log();

//        StepVerifier
//                .create(flux)
//                .expectNextMatches(n -> n == 2)
//                .expectNextMatches(n -> n == 4)
//                .expectNextMatches(n -> n == 6)
//                .expectComplete()
//                .verify();
    }

    private Mono<String> strings(String id) {
        var emp = Map.of("1", "Lucas", "2", "Júlia");
        return Mono.just(emp.get(id));

    }

}
