package com.playtray.repository;

import com.playtray.model.entity.Cart;
import com.playtray.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByCustomer(User user);
}
