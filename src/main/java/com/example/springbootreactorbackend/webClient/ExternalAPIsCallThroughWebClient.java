package com.example.springbootreactorbackend.webClient;

import com.example.springbootreactorbackend.models.Model;
import com.example.springbootreactorbackend.utilities.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.net.ConnectException;
import java.time.Duration;
import java.util.List;

public class ExternalAPIsCallThroughWebClient extends  ReactiveWebClient  {
    @Autowired
    Log log;
    protected ExternalAPIsCallThroughWebClient(WebClient.Builder webClientBuilder) {
        super(webClientBuilder);
    }

    public Mono<List<Model>> getData() {
        return webClientBuilder
                .build()
                .get()
                .uri("http://example.com/get")
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Model>>() {})
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(3))
                        .filter(throwable ->
                        {
                            log.debug("Error");
                           return throwable.getCause().getCause() instanceof ConnectException;

                        }
                        )
                )
                .timeout(Duration.ofSeconds(3));
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
