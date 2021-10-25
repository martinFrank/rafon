package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class PlayerService extends CrudService<Player, Integer> {

    private PlayerRepository repository;

    public PlayerService(@Autowired PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    protected PlayerRepository getRepository() {
        return repository;
    }

}
