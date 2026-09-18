package com.enigmacamp.koperasiKita.repository;

import com.enigmacamp.koperasiKita.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
//    @Query("SELECT MAX()")

}
