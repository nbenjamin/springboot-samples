package com.nbenja.springboot.resilience.controller;

import com.nbenja.springboot.resilience.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    private DemoService demoService;

    public CircuitBreakerController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/circuitbreaker/{delay}")
    public String resilience4J(@PathVariable("delay") String delay) {
        return demoService.circuitBreaker(delay);
    }

}
