package com.nbenja.springboot.resilience.controller;

import com.nbenja.springboot.resilience.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterController {

    private DemoService demoService;

    public RateLimiterController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/ratelimiter/{delay}")
    public String rateLimiter(@PathVariable("delay") String delay) {
        return demoService.rateLimiter(delay);
    }

}


