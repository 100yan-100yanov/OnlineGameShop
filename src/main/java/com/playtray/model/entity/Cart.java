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
public class Cart extends BaseEntity {

    @OneToOne(targetEntity = User.class)
    private User customer;

    @OneToMany
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

    public Item getItem(Product product) {

        for (Item item : items) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
    }
}
