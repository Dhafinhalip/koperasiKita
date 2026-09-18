package com.enigmacamp.koperasiKita.service.impl;

import com.enigmacamp.koperasiKita.dto.request.SearchProductRequest;
import com.enigmacamp.koperasiKita.dto.response.ProductResponse;
import com.enigmacamp.koperasiKita.mapper.ProductMapper;
import com.enigmacamp.koperasiKita.model.Product;
import com.enigmacamp.koperasiKita.repository.ProductRepository;
import com.enigmacamp.koperasiKita.service.ProductService;
import com.enigmacamp.koperasiKita.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    @Transactional
    public ProductResponse create(Product product) {
        validate(product);

        product.setCreatedAt(LocalDateTime.now());

        product.setCreatedBy("system");

        Product saveProduct = productRepository.save(product);

        return ProductMapper.convertToProductResponse(saveProduct);
    }

    @Override
    public ProductResponse getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID Cannot Be Empty Or Smaller Than Or Equal To Zero");
        }

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new NullPointerException("Product With ID " + id + " Not Found");
        }

        return ProductMapper.convertToProductResponse(product.get());
    }

    @Override
    @Transactional
    public ProductResponse updateById(Long id, Product product) {
        Optional<Product> oldProduct = productRepository.findById(id);

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
        updatedProduct.setUpdatedAt(LocalDateTime.now());
        updatedProduct.setUpdatedBy("system");

        validate(updatedProduct);

        Product newProduct = productRepository.save(updatedProduct);

        return ProductMapper.convertToProductResponse(newProduct);
    }


    @Override
    @Transactional
    public ProductResponse deleteById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product Stock Cannot Be Empty Or Smaller Than Or Equal To Zero");
        }

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new NullPointerException("Product With ID " + id + " Not Found");
        }

        productRepository.deleteById(id);

        return null;
    }

    @Override
    public List<ProductResponse> searchProducts(SearchProductRequest request) {
        Specification<Product> specification = ProductSpecification.getSpesification(request);

        List<Product> products = productRepository.findAll(specification);

        return ProductMapper.convertToListOfProductResponse(products);
    }

    @Override
    public Page<ProductResponse> searchProductWithPagination(SearchProductRequest request) {
        int page = (request.getPage() == null || request.getPage() <=0) ? 0 : request.getPage() - 1;
        int size = (request.getPage() == null || request.getPage() <=0) ? 10 : request.getSize();
        String sortBy = (request.getSortBy() == null || request.getSortBy().isEmpty())? "name" : request.getSortBy();
        String direction = (request.getDirection() == null || request.getDirection().isEmpty()) ? "asc" : request.getDirection();

        Specification<Product> specification = ProductSpecification.getSpesification(request);

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<Product> productPage = productRepository.findAll(specification, pageRequest);

        List<ProductResponse> productResponses = ProductMapper.convertToListOfProductResponse(productPage.getContent());

        return new PageImpl<>(productResponses, pageRequest, productPage.getTotalElements());
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
