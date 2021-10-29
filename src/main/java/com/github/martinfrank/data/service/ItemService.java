package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class ItemService extends CrudService<Item, Integer> {

    private ItemRepository repository;

    public ItemService(@Autowired ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ItemRepository getRepository() {
        return repository;
    }

}
