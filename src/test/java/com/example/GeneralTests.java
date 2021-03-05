package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GeneralTests {

    @Test
    void testSubscribers() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4);

        just.subscribe(System.out::println);
    }

    @Test
    void testSubscribersWithError() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4)
                .map(v -> {
                    if (v == 4) {
                        throw new RuntimeException("Error =/");
                    }
                    return v;
                });

        just.subscribe(System.out::println,
                error -> System.out.println(error));
    }

    @Test
    void testSubscriberWithComplete() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4);

        just.subscribe(System.out::println,
                System.out::println,
                () -> System.out.println("Completou!"));

    }

    @Test
    void testSubscriberWithSubscriptionConsumer() {
        Flux<Integer> just = Flux.range(1, 20);

        just.subscribe(System.out::println,
                System.out::println,
                () -> System.out.println("Completou!"),
                sub -> sub.request(15));
    }

    @Test
    void flatmapTest() {
        Flux<Integer> just = Flux.range(1, 10);



    }


}
