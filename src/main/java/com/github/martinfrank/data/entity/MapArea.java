package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class MapArea extends AbstractEntity {

    @NotEmpty
    private String mapAreaName = "";

}
