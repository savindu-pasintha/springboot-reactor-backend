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
                .GET("/get", handler::get)
                .POST("/post", RequestPredicates.accept(MediaType.APPLICATION_JSON), handler::post)
                .PUT("/put", RequestPredicates.accept(MediaType.APPLICATION_JSON), handler::put)
                .DELETE("/delete", RequestPredicates.accept(MediaType.APPLICATION_JSON), handler::delete)
                .PATCH("/patch", RequestPredicates.accept(MediaType.APPLICATION_JSON), handler::patch)
                .OPTIONS("/options", RequestPredicates.accept(MediaType.APPLICATION_JSON), handler::options)
                .HEAD("/head", handler::head)
//                .TRACE("/trace", handler::trace)
                .build();
    }

}
