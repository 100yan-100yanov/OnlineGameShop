package com.playtray.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity{

    @OneToOne(targetEntity = User.class)
    private User customer;

    @OneToMany
    private List<Product> products;

    private int quantity;

    private BigDecimal totalPrice;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public User getCustomer() {
        return customer;
    }

    public Cart setCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Cart setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Cart setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Cart setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
