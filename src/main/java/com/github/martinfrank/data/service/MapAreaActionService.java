package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.MapAreaAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class MapAreaActionService extends CrudService<MapAreaAction, Integer> {

    private MapAreaActionRepository repository;

    public MapAreaActionService(@Autowired MapAreaActionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected MapAreaActionRepository getRepository() {
        return repository;
    }

}
