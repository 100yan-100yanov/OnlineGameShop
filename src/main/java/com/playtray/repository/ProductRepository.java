package com.playtray.repository;

import com.playtray.model.entity.Product;
import com.playtray.model.enums.ProductCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByProductCategory(ProductCategory category, Pageable pageable);
}
