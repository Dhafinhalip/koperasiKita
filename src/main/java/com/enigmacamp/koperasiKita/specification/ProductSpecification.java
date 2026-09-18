package com.enigmacamp.koperasiKita.specification;

import com.enigmacamp.koperasiKita.dto.request.SearchProductRequest;
import com.enigmacamp.koperasiKita.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public  static Specification<Product> getSpesification(SearchProductRequest request) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null && !request.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }

            if (request.getCategory() != null && !request.getCategory().isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("category")), request.getCategory().toUpperCase()));
            }

            if (request.getIsAvailable() != null && !request.getIsAvailable().isEmpty() && (request.getIsAvailable().equalsIgnoreCase("true") || request.getIsAvailable().equalsIgnoreCase("false"))) {
                boolean isAvailableInput = Boolean.parseBoolean(request.getIsAvailable());
                predicates.add(criteriaBuilder.equal(root.get("isAvailable"), isAvailableInput));
            }

            //minimum price
            if (request.getMinPrice() != null && !request.getMinPrice().isEmpty()) {
                BigDecimal minPrice = new BigDecimal(request.getMinPrice());

                if (minPrice.compareTo(BigDecimal.ZERO) > 0) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
                }

            }

            //maximum price
            if (request.getMaxPrice() != null && !request.getMaxPrice().isEmpty()) {
                BigDecimal price = new BigDecimal(request.getMaxPrice());

                if (price.compareTo(BigDecimal.ZERO) > 0) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
                }

            }

            //minimum stock
            if (request.getMinStock() != null && !request.getMinStock().isEmpty()) {
                int stock = Integer.parseInt(request.getMinStock());

                if (stock > 0) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stock));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });
    }
}
