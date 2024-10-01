package com.gontijo.product.repository;

import com.gontijo.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUserId(Long userId);
}
