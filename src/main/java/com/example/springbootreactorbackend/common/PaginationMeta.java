package com.example.springbootreactorbackend.common;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationMeta {

    private int currentPage;

    private int pageSize;

    private long totalElements;

    private int totalPages;
}

