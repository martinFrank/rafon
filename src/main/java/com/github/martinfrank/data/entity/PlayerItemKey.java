package com.github.martinfrank.data.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PlayerItemKey implements Serializable {

    @Column(name = "player_id")
    Long playerId;

    @Column(name = "item_id")
    Long itemId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}