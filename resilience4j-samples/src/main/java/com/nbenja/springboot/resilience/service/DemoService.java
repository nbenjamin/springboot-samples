package com.nbenja.springboot.resilience.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;


@Component
public class DemoService {

    @CircuitBreaker(name = "demo", fallbackMethod = "fallback")
    public String circuitBreaker(String id) {
        System.out.println("CircuitBreaker ");
        if(id.equals("0")) {
            throw new ClassCastException("Test");
        }
        return "Success";
    }

    @RateLimiter(name = "rl", fallbackMethod = "fallback")
    public String rateLimiter(String id) {
        System.out.println("rateLimiter");
        return "Success";
    }

    @Retry(name = "retryService", fallbackMethod = "fallback")
    public String retryService(String id) {
        System.out.println("retryService");
        if(id.equals("0")) {
            throw new ClassCastException("Test");
        }
        return "Success";
    }


    @Bulkhead(name = "service1", fallbackMethod = "fallback")
    public String bulkHeadServiceOne(String id) {
        System.out.println("bulk head Service 1");
        return "BulkHead Service One";
    }


    public String fallback(String id, Throwable e) {
        System.out.println("Fallback ");
        return "FallBack";
    }

}