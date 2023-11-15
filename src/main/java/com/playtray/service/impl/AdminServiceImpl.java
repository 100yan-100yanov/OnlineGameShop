package com.playtray.service.impl;

import com.playtray.model.dto.ProductAddDTO;
import com.playtray.model.dto.UserDeleteDTO;
import com.playtray.model.entity.Product;
import com.playtray.model.entity.User;
import com.playtray.repository.ProductRepository;
import com.playtray.repository.UserRepository;
import com.playtray.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public AdminServiceImpl(UserRepository userRepository,
                            ProductRepository productRepository,
                            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteUser(UserDeleteDTO userDeleteDTO) {
        Optional<User> optionalUser = userRepository.findByUsername(userDeleteDTO.getUsername);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            userRepository.delete(user);
        }
    }

    @Override
    public void addProduct(ProductAddDTO productAddDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productAddDTO.getId);

        if (optionalProduct.isEmpty()) {
            Product product = modelMapper.map(productAddDTO, Product.class);

            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(ProductAddDTO productAddDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productAddDTO.getId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            productRepository.delete(product);
        }
    }
}
