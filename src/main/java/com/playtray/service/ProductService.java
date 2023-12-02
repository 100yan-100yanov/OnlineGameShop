package com.playtray.service;

import com.playtray.model.dto.ProductAddDTO;
import com.playtray.model.dto.ProductDTO;
import com.playtray.model.dto.ProductDetailsDTO;
import com.playtray.model.dto.ProductSummaryDTO;
import com.playtray.model.entity.Product;
import com.playtray.model.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProductService {

    void add(ProductAddDTO productAddDTO);

    void delete(Long id);

    Page<ProductSummaryDTO> getAll(ProductCategory category, Pageable pageable);

    Optional<ProductDetailsDTO> getProductDetails(Long id);

    Product findById(Long productId);

    ProductDTO findLatest(ProductCategory productCategory);
}
