package com.playtray.service.impl;

import com.playtray.model.entity.Item;
import com.playtray.repository.ItemRepository;
import com.playtray.service.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
