package com.enigmacamp.koperasiKita.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductRequest {
    private String name;
    private String category;
    private String maxPrice;
    private String minPrice;
    private String minStock;
    private String isAvailable;

    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
