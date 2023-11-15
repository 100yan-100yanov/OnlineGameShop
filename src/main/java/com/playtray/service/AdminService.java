package com.playtray.service;

import com.playtray.model.dto.ProductAddDTO;
import com.playtray.model.dto.UserDeleteDTO;

public interface AdminService {
    void deleteUser(UserDeleteDTO userDeleteDTO);

    void addProduct(ProductAddDTO productAddDTO);

    void deleteProduct(ProductAddDTO productAddDTO);
}
