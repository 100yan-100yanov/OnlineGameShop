package com.playtray.model.dto;

import com.playtray.model.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public record CartBuyDTO(List<Item> items,
                         BigDecimal totalPrice) {
}
