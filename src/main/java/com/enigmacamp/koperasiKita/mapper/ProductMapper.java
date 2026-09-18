package com.enigmacamp.koperasiKita.mapper;

import com.enigmacamp.koperasiKita.dto.response.ProductResponse;
import com.enigmacamp.koperasiKita.model.Product;

import java.util.List;

public class ProductMapper {
    public static ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .stock(product.getStock())
                .isAvailable(product.getIsAvailable())
                .createdAt(product.getCreatedAt())
                .createdBy(product.getCreatedBy())
                .updatedAt(product.getUpdatedAt())
                .updatedBy(product.getUpdatedBy())
                .deleteddAt(product.getDeleteddAt())
                .deletedBy(product.getDeletedBy())
                .build();
    }

    public static List<ProductResponse> convertToListOfProductResponse(List<Product> products) {
        return products.stream().map(ProductMapper::convertToProductResponse).toList();
    }

}
