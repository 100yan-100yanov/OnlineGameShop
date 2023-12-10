package com.playtray.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item extends BaseEntity{

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;


    public Product getProduct() {
        return product;
    }

    public Item setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        this.setPrice();
        return price;
    }

    public void setPrice() {
        this.price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
