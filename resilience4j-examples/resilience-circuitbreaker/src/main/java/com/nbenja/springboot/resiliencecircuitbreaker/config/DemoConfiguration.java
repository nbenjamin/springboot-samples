package com.nbenja.springboot.resiliencecircuitbreaker.config;

import com.nbenja.springboot.resiliencecircuitbreaker.handler.DemoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class DemoConfiguration {


    @Bean
    public RouterFunction<ServerResponse> routerFunction(DemoHandler demoHandler) {
        return RouterFunctions.route(
                GET("/demo/{count}")
                        .and(accept(MediaType.APPLICATION_JSON)),
                demoHandler::demo);
    }

}
