package com.github.martinfrank.data.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PlayerItemKey implements Serializable {

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "item_id")
    private Long itemId;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}