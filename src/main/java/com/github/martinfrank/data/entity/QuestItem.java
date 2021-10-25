package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class QuestItem extends AbstractEntity {

    @NotEmpty
    private String name = "";

    @ManyToOne
    @JoinColumn(name="player", nullable=false)
    private Player player;

    @ManyToMany
    @JoinTable(
            name = "grantedAccess",
            joinColumns = @JoinColumn(name = "quest_item_id"),
            inverseJoinColumns = @JoinColumn(name = "map_area_id"))
    private Set<MapArea> grantedAccess;



}
