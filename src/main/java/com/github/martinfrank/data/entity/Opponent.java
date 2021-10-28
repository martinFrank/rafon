package com.github.martinfrank.data.entity;


import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Opponent extends AbstractEntity {

    @NotEmpty
    private String name;

    private double maxLife;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(double maxLife) {
        this.maxLife = maxLife;
    }
}
