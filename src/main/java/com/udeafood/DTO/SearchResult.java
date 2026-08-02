package com.udeafood.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class SearchResult<T> {
    private List<T> results;
    private int page;     // Current page number (start from 0)
    private int size;       // Page size (how many elements per page)
    private long totalElements; // Total elements available
    private int totalPages;     // Total pages available


    public SearchResult(List<T> results, int page, int size, long totalElements, int totalPages) {
        this.results = results;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages; //(int) Math.ceil((double) totalElements / size);
    }
}
