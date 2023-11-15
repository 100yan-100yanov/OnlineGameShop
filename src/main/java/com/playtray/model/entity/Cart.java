package com.playtray.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity{

    @OneToOne
    private User customer;

    @OneToMany
    private List<Product> products;

    @Min(1)
    private int quantity;

    private BigDecimal totalPrice;

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
