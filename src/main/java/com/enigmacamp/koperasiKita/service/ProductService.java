package com.enigmacamp.koperasiKita.service;

import com.enigmacamp.koperasiKita.dto.request.SearchProductRequest;
import com.enigmacamp.koperasiKita.dto.response.ProductResponse;
import com.enigmacamp.koperasiKita.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product product);
    Optional<Product> getById(Long id);
    Product updateById(Long id, Product product);
    void deleteById(Long id);
    List<ProductResponse> searchProducts(SearchProductRequest request);
    Page<ProductResponse> searchProductWithPagination(SearchProductRequest request);
}
