package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.Combat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class CombatService extends CrudService<Combat, Integer> {

    private CombatRepository repository;

    public CombatService(@Autowired CombatRepository repository) {
        this.repository = repository;
    }

    @Override
    protected CombatRepository getRepository() {
        return repository;
    }

}
