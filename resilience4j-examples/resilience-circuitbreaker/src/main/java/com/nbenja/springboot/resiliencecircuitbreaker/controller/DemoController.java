package com.nbenja.springboot.resiliencecircuitbreaker.controller;

import com.nbenja.springboot.resiliencecircuitbreaker.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/test/{delay}")
    public String resilience4J(@PathVariable("delay") String delay) {
        return demoService.demo(delay).block();
    }

}