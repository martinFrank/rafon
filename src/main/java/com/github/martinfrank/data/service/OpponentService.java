package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.Opponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class OpponentService extends CrudService<Opponent, Integer> {

    private OpponentRepository repository;

    public OpponentService(@Autowired OpponentRepository repository) {
        this.repository = repository;
    }

    @Override
    protected OpponentRepository getRepository() {
        return repository;
    }

}
