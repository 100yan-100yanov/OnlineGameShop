package com.playtray.model.dto;

import com.playtray.model.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO {
    private List<Item> items;
    private BigDecimal totalPrice;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemsQuantity() {

        return this.items
                .stream()
                .map(Item::getQuantity)
                .reduce(0, Integer::sum);
    }
}
