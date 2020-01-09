package com.nbenja.springboot.resiliencecircuitbreaker.handler;

import com.nbenja.springboot.resiliencecircuitbreaker.service.DemoService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DemoHandler {

    private DemoService demoService;

    public DemoHandler(DemoService demoService) {
        this.demoService = demoService;
    }

    public Mono<ServerResponse> demo(ServerRequest serverRequest) {
        Mono<String> response = demoService.demo(serverRequest.pathVariable("count"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, String.class);
    }

}
