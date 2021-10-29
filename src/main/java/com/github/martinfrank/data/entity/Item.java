package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Item extends AbstractEntity {

    @NotEmpty
    private String name = "";

    @OneToMany(mappedBy = "item", cascade = CascadeType.MERGE)
    Set<PlayerItem> inventory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlayerItem> getInventory() {
        return inventory;
    }

    public void setInventory(Set<PlayerItem> inventory) {
        this.inventory = inventory;
    }
}
