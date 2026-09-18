package com.enigmacamp.koperasiKita.controller;

import com.enigmacamp.koperasiKita.dto.request.ProductRequest;
import com.enigmacamp.koperasiKita.dto.request.SearchProductRequest;
import com.enigmacamp.koperasiKita.dto.response.CommonResponse;
import com.enigmacamp.koperasiKita.dto.response.ProductResponse;
import com.enigmacamp.koperasiKita.model.Product;
import com.enigmacamp.koperasiKita.service.ProductService;
import com.enigmacamp.koperasiKita.utils.ResponseUtil;
import com.enigmacamp.koperasiKita.utils.constant.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Create Data
    @PostMapping
    public ResponseEntity<CommonResponse<ProductResponse>> createProduct(@RequestBody ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .stock(request.getStock())
                .isAvailable(request.getIsAvailable())
                .build();

        ProductResponse productResponse = productService.create(product);

        return ResponseUtil.buildResponse(HttpStatus.CREATED, ResponseMessage.SUCCESS_CREATE_DATA, productResponse);
    }

    //Get Data By Id
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductResponse>> getProductById(@PathVariable Long id) {

        ProductResponse productResponse = productService.getById(id);

        return ResponseUtil.buildResponse(HttpStatus.OK, ResponseMessage.SUCCESS_FOUND_DATA, productResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductResponse>> updateProductById(@PathVariable Long id,  @RequestBody ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .stock(request.getStock())
                .isAvailable(request.getIsAvailable())
                .build();

        ProductResponse productResponse = productService.updateById(id, product);

        return ResponseUtil.buildResponse(HttpStatus.OK, ResponseMessage.SUCCESS_UPDATE_DATA, productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductResponse>> deleteProductById(@PathVariable Long id) {

        ProductResponse productResponse = productService.deleteById(id);

        return ResponseUtil.buildResponse(HttpStatus.NOT_FOUND, ResponseMessage.FAILED_FOUND_DATA, productResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponse<List<ProductResponse>>> searchWithPaging(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String minStock,
            @RequestParam(required = false) String isAvailable,

            //For Pagination
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction
    ) {

        SearchProductRequest request = SearchProductRequest.builder()
                .name(name)
                .category(category)
                .maxPrice(maxPrice)
                .minPrice(minPrice)
                .minStock(minStock)
                .isAvailable(isAvailable)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();

        Page<ProductResponse> productResponses = productService.searchProductWithPagination(request);

        return ResponseUtil.buildResponse(HttpStatus.OK, ResponseMessage.SUCCESS_RETRIEVE_DATA, productResponses.getContent(), productResponses);
    }

}
