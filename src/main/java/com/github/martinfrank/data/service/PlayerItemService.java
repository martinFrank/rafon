package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.PlayerItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class PlayerItemService extends CrudService<PlayerItem, Integer> {

    private PlayerItemRepository repository;

    public PlayerItemService(@Autowired PlayerItemRepository repository) {
        this.repository = repository;
    }

    @Override
    protected PlayerItemRepository getRepository() {
        return repository;
    }

}
