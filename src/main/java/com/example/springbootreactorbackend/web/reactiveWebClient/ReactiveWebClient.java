package com.example.springbootreactorbackend.web.reactiveWebClient;

import org.springframework.web.reactive.function.client.WebClient;

public abstract class ReactiveWebClient{
    protected final WebClient.Builder webClientBuilder;

    protected ReactiveWebClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
}
