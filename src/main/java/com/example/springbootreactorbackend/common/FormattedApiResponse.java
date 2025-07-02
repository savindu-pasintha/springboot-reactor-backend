package com.example.springbootreactorbackend.common;

import lombok.*;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormattedApiResponse<T> {

    private boolean success;

    private String message;

    private T data;

    private Map<String, String> errors;

    private Instant timestamp;

    private String traceId;

    private PaginationMeta pagination;

    public static <T> FormattedApiResponse<T> success(T data, String message) {
        return FormattedApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> FormattedApiResponse<T> successWithPagination(T data, String message, PaginationMeta pagination) {
        return FormattedApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .pagination(pagination)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> FormattedApiResponse<T> failure(String message, Map<String, String> errors) {
        return FormattedApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errors(errors)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> FormattedApiResponse<T> error(String message, String traceId) {
        return FormattedApiResponse.<T>builder()
                .success(false)
                .message(message)
                .traceId(traceId)
                .timestamp(Instant.now())
                .build();
    }
}
