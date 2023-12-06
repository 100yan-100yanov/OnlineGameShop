package com.playtray.service.impl;

import com.playtray.repository.CartRepository;
import com.playtray.repository.ProductRepository;
import com.playtray.repository.UserRepository;
import com.playtray.service.CartService;
import com.playtray.service.ItemService;
import com.playtray.service.ProductService;
import com.playtray.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.test.context.support.WithMockUser;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    private CartService serviceTest;

    @Mock
    private CartRepository mockedCartRepository;
    @Mock
    private ProductService mockedProductService;
    @Mock
    private UserService mockedUserService;
    @Mock
    private ItemService mockedItemService;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        serviceTest = new CartServiceImpl(
                mockedCartRepository,
                mockedProductService,
                mockedUserService,
                mockedItemService,
                modelMapper);
    }

    @Test
    @WithMockUser(username = "TestUser")
    void testAddToCart() {

    }
}
