package com.playtray.service.impl;

import com.playtray.model.dto.ProductAddDTO;
import com.playtray.model.entity.Product;
import com.playtray.repository.ProductRepository;
import com.playtray.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(ProductAddDTO productAddDTO) {
        Product product = modelMapper.map(productAddDTO, Product.class);

        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new NullPointerException("Product with id " + id + " doesn't exist!");
        }

        productRepository.delete(product.get());
    }
}
