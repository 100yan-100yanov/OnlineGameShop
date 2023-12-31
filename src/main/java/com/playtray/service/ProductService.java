package com.playtray.service;

import com.playtray.model.dto.ProductAddDTO;
import com.playtray.model.dto.ProductLatestDTO;
import com.playtray.model.dto.ProductDetailsDTO;
import com.playtray.model.dto.ProductSummaryDTO;
import com.playtray.model.entity.Product;
import com.playtray.model.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    void add(ProductAddDTO productAddDTO);

    void delete(Long id);

    Page<ProductSummaryDTO> getAll(ProductCategory category, Pageable pageable);

    ProductDetailsDTO getProductDetails(Long id);

    Product findById(Long productId);

    ProductLatestDTO findLatest(ProductCategory productCategory);
}
