package com.github.martinfrank.data.entity;


import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Opponent extends AbstractEntity {

    @NotEmpty
    private String name;

    private Long maxLife;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(Long maxLife) {
        this.maxLife = maxLife;
    }
}
