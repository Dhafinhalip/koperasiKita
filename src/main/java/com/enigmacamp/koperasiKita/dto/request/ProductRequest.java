package com.enigmacamp.koperasiKita.dto.request;

import com.enigmacamp.koperasiKita.utils.validators.ValidProductAvailable;
import com.enigmacamp.koperasiKita.utils.validators.ValidationGroups;
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
    @NotNull (message = "Product Name Cannot Be Null", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    @NotBlank (message = "Product Name Cannot Be Empty", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    private String name;

    @NotNull (message = "Product Description Cannot Be Null", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    @NotBlank (message = "Product Description Cannot Be Empty", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    private String description;

    @NotNull (message = "Product Price Cannot Be NULL", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    @Min(0)  @Min(value = 0, message = "Product Stock Cannot Be Minus", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    private BigDecimal price;

    @NotNull (message = "Product Cateogory Cannot Be Null", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    @NotBlank (message = "Product Category Cannot Be Empty", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    private String category;

    @NotNull (message = "Product Stock Cannot Be Null", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    @Min(value = 0, message = "Product Stock Cannot Be Minus", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    private Integer stock;

    @NotNull (message = "Product Available Note Cannot Be Empty", groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    @ValidProductAvailable (groups = {ValidationGroups.onCreate.class, ValidationGroups.onUpdate.class})
    private String isAvailable;
}
