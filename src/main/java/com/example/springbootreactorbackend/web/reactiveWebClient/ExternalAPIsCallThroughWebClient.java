package com.example.springbootreactorbackend.web.reactiveWebClient;

import com.example.springbootreactorbackend.models.Model;
import com.example.springbootreactorbackend.utilities.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.net.ConnectException;
import java.time.Duration;
import java.util.List;

@Service
public class ExternalAPIsCallThroughWebClient extends ReactiveWebClient {

    private final WebClient webClient;

    @Autowired
    Log log;

    @Autowired
    public ExternalAPIsCallThroughWebClient(WebClient.Builder webClientBuilder) {
        super(webClientBuilder);
        this.webClient = webClientBuilder.build();
    }

    public Mono<List<Model>> getApiData() {
        return webClient
                .get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Model>>() {})
                .timeout(Duration.ofSeconds(2))
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(10))
                                .filter(throwable -> {
                                    log.debug("Retry error: {}" + throwable.toString());
                                    return throwable instanceof ConnectException;
                                })
                )
                ;
    }

    public Mono<List<Model>> postData(Model passDataASBodyValue) {
        return webClientBuilder
                .build()
                .post()
                .uri("http://example.com/post")
                .bodyValue(passDataASBodyValue)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Model>>() {})
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(3))
                        .filter(throwable -> throwable.getCause().getCause() instanceof ConnectException)
                )
                .timeout(Duration.ofSeconds(3));
    }
}
