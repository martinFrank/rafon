package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Item extends AbstractEntity {

    @NotEmpty
    private String name = "";

//    @OneToMany(mappedBy = "item")
//    Set<PlayerItem> inventory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
