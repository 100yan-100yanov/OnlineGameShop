package com.playtray.service;

import com.playtray.model.dto.ProductSummaryDTO;
import com.playtray.model.dto.ProductAddDTO;
import com.playtray.model.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    void add(ProductAddDTO productAddDTO);

    void delete(Long id);

    Page<ProductSummaryDTO> getAllProducts(ProductCategory category, Pageable pageable);
}
