package com.nbenja.springboot.resiliencecircuitbreaker.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@CircuitBreaker(name = "demo")
public class DemoService {

    @CircuitBreaker(name = "demo", fallbackMethod = "fallback")
    public Mono<String> demo(String id) {
        System.out.println("demo ");
        if(id.equals("0")) {
            throw new ClassCastException("Test");
        }
        return Mono.just("Success");
    }


    public Mono<String> fallback(String id, Throwable e) {
        System.out.println("Fallback ");
        return Mono.just("FallBack");
    }

}
