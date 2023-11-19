package com.playtray.service.impl;

import com.playtray.model.dto.ProductSummaryDTO;
import com.playtray.model.dto.ProductAddDTO;
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

    @Override
    public Page<ProductSummaryDTO> getAllProducts(ProductCategory category, Pageable pageable) {

        List<ProductSummaryDTO> products = productRepository
                .findAllByProductCategory(category, pageable)
                .stream()
                .map(ProductServiceImpl::mapToSummary)
                .toList();

        return new PageImpl<>(products);
    }

    private static ProductSummaryDTO mapToSummary(Product product) {
        return new ProductSummaryDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPlatform(),
                product.getPrice(),
                product.getDescription()
        );
    }
}
