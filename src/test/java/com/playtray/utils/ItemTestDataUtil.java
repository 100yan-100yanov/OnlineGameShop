package com.playtray.utils;

import com.playtray.model.entity.Item;
import com.playtray.repository.ItemRepository;
import com.playtray.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemTestDataUtil {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Item createItem() {
        Item item = new Item();

        item
                .setProduct(productRepository.findAll().get(0))
                .setQuantity(1)
                .setPrice();

        return itemRepository.save(item);
    }

    public void cleanUp() {
        itemRepository.deleteAll();
    }
}
