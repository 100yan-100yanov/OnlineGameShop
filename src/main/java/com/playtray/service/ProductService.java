package com.playtray.service;

import com.playtray.model.dto.ProductAddDTO;

public interface ProductService {

    void add(ProductAddDTO productAddDTO);

    void delete(Long id);
}
