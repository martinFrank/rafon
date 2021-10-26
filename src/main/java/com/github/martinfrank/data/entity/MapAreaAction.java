package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.validation.constraints.NotEmpty;

public class MapAreaAction extends AbstractEntity {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
