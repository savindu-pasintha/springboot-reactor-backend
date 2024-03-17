package com.example.springbootreactorbackend;


import com.example.springbootreactorbackend.handlers.ExampleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class HttpRouter {
    private final ExampleHandler exampleHandler;
    public HttpRouter(ExampleHandler exampleHandler) {
        this.exampleHandler = exampleHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routes(ExampleHandler handler) {
        return route()
                .GET(
                       "/hello",
//                        RequestPredicates.accept(MediaType.APPLICATION_JSON),
                        handler::get)
                .POST(
                        "/post",
                        RequestPredicates.accept(MediaType.APPLICATION_JSON),
                        handler::post)
                .build();
//                .GET("/api/example").and(contentType(MediaType.TEXT_PLAIN)), handler::handleGet)
//                .andRoute(POST("/api/example").and(contentType(MediaType.APPLICATION_JSON)), handler::handlePost);
    }

//    @Bean
//    public RouterFunction<ServerResponse> routes(ExampleHandler handler) {
//
//        return RouterFunctions
//                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), handler::handleGet);
//    }


//    @Bean
//    public ExampleHandler exampleHandler() {
//        return new ExampleHandler();
//    }
}
