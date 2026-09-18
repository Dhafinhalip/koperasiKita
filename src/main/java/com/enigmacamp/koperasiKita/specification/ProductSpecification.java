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
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));
            }

            if (request.getCategory() != null && !request.getCategory().isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("category")), request.getCategory().toUpperCase()));
            }

            if (request.getIsAvailable() != null && !request.getIsAvailable().isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("isAvailable")), request.getIsAvailable().toLowerCase()));
            }

            //minimum price
            if (request.getMinPrice() != null && !request.getMinPrice().isEmpty()) {
                BigDecimal price = new BigDecimal(request.getMinPrice());

                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
            }

            //maximum price
            if (request.getMaxPrice() != null && !request.getMaxPrice().isEmpty()) {
                BigDecimal price = new BigDecimal(request.getMaxPrice());

                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
            }

            //minimum stock
            if (request.getMinPrice() != null && !request.getMinPrice().isEmpty()) {
                Integer stock = Integer.parseInt(request.getMinPrice());

                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stock));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });
    }
}
