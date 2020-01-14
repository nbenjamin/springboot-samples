package com.nbenja.springboot.resilience.controller;

import com.nbenja.springboot.resilience.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulkHeadController {

    private DemoService demoService;

    public BulkHeadController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/bulk/{delay}")
    public String bulkHead(@PathVariable("delay") String delay) {
        return demoService.bulkHeadServiceOne(delay);
    }

}
