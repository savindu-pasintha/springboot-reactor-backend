package com.example.springbootreactorbackend.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ExampleHandler {

    public Mono<ServerResponse> get(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .bodyValue("{\"message\": \"GET request handled\"}");
    }

    public Mono<ServerResponse> post(ServerRequest request) {
        return request.bodyToMono(String.class)
                .flatMap(body -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("{\"message\": \"POST request handled with body: " + body + "\"}"));
    }
}

