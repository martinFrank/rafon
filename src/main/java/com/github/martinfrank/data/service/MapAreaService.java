package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.MapArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class MapAreaService extends CrudService<MapArea, Integer> {

    private MapAreaRepository repository;

    public MapAreaService(@Autowired MapAreaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected MapAreaRepository getRepository() {
        return repository;
    }

}
