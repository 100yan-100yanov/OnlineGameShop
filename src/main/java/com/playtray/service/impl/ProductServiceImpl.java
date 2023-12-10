package com.playtray.service.impl;

import com.playtray.service.exception.ObjectNotFoundException;
import com.playtray.model.dto.ProductAddDTO;
import com.playtray.model.dto.ProductLatestDTO;
import com.playtray.model.dto.ProductDetailsDTO;
import com.playtray.model.dto.ProductSummaryDTO;
import com.playtray.model.entity.Product;
import com.playtray.model.enums.ProductCategory;
import com.playtray.repository.ProductRepository;
import com.playtray.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.playtray.constants.ExceptionMessages.PRODUCT_ID_NOT_FOUND;

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
    public void delete(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new ObjectNotFoundException(PRODUCT_ID_NOT_FOUND + productId);
        }

        productRepository.delete(product.get());
    }

    @Override
    public Page<ProductSummaryDTO> getAll(ProductCategory category, Pageable pageable) {

        List<ProductSummaryDTO> products = productRepository
                .findAllByProductCategory(category, pageable)
                .stream()
                .map(product -> modelMapper.map(product, ProductSummaryDTO.class))
                .toList();

        return new PageImpl<>(products);
    }

    @Override
    public ProductDetailsDTO getProductDetails(Long productId) {
        return productRepository
                .findById(productId)
                .map(product -> modelMapper.map(product, ProductDetailsDTO.class))
                .orElseThrow(() ->
                        new ObjectNotFoundException(PRODUCT_ID_NOT_FOUND + productId));
    }

    @Override
    public Product findById(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ObjectNotFoundException(PRODUCT_ID_NOT_FOUND + productId));
    }

    @Override
    public ProductLatestDTO findLatest(ProductCategory productCategory) {
        Product product = productRepository.findProductByCategory(productCategory);

        return modelMapper.map(product, ProductLatestDTO.class);
    }
}
