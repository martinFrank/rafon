package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Item extends AbstractEntity {

    @NotEmpty
    private String name = "";

    @OneToMany(mappedBy = "item")
    Set<PlayerItem> playerItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlayerItem> getPlayerItems() {
        return playerItems;
    }

    public void setPlayerItems(Set<PlayerItem> playerItems) {
        this.playerItems = playerItems;
    }
}
