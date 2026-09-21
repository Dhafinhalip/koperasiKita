package com.enigmacamp.koperasiKita.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @NotNull (message = "Product Name Cannot Be Empty")
    @NotBlank (message = "Product Name Cannot Be Empty")
    private String name;

    @NotNull (message = "Product Description Cannot Be Empty")
    @NotBlank (message = "Product Description Cannot Be Empty")
    private String description;

    @NotNull (message = "Product Price Cannot Be NULL")
    @Min(0)
    private BigDecimal price;

    @NotNull (message = "Product Cateogory Cannot Be Empty")
    @NotBlank (message = "Product Category Cannot Be Empty")
    private String category;

    @NotNull (message = "Product Stock Cannot Be Empty")
    @Min(value = 0, message = "Product Stock Cannot Be Minus")
    private Integer stock;

    @NotNull (message = "Product Available Note Cannot Be Empty")
    private String isAvailable;
}
