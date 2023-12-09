package com.playtray.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @OneToOne(targetEntity = User.class)
    private User customer;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Item> items;

    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public User getCustomer() {
        return customer;
    }

    public Cart setCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public Cart setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {

        this.totalPrice = items
                .stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
