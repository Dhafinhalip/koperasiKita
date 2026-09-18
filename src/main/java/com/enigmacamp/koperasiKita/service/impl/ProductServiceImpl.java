package com.enigmacamp.koperasiKita.service.impl;

import com.enigmacamp.koperasiKita.dto.request.SearchProductRequest;
import com.enigmacamp.koperasiKita.dto.response.ProductResponse;
import com.enigmacamp.koperasiKita.model.Product;
import com.enigmacamp.koperasiKita.repository.ProductRepository;
import com.enigmacamp.koperasiKita.service.ProductService;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product create(Product product) {

        validate(product);

        product.setCreatedBy("system");

        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID Cannot Be Empty Or Smaller Than Or Equal To Zero");
        }

        return productRepository.findById(id);
    }

    @Override
    public Product updateById(Long id, Product product) {
        Optional<Product> oldProduct = getById(id);

        if (oldProduct.isEmpty()) {
            throw new NullPointerException("Product With ID " + product.getId() + " Not Found");
        }

        Product updatedProduct = oldProduct.get();

        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setStock(product.getStock());
        updatedProduct.setIsAvailable(product.getIsAvailable());
        updatedProduct.setUpdatedBy("system");

        validate(updatedProduct);

        return productRepository.save(updatedProduct);
    }


    @Override
    public void deleteById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product Stock Cannot Be Empty Or Smaller Than Or Equal To Zero");
        }

        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> searchProducts(SearchProductRequest request) {
        return List.of();
    }

    @Override
    public Page<ProductResponse> searchProductWithPagination(SearchProductRequest request) {
        return null;
    }

    private void validate(Product product) {

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product Name Cannot Be Empty");
        }

        if (product.getDescription() == null || product.getDescription().isEmpty() ) {
            throw new IllegalArgumentException("Product Description Cannot Be Empty");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product Price Cannot Be Empty Or Smaller Than Or Equal To Zero");
        }

        if (product.getCategory() == null || product.getCategory().isEmpty() ) {
            throw new IllegalArgumentException("Product Category Cannot Be Empty");
        }

        if (product.getStock() == null || product.getStock() <= 0) {
            throw new IllegalArgumentException("Product Stock Cannot Be Empty Or Smaller Than Or Equal To Zero");
        }
    }
}
