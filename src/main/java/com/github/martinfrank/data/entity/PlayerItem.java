package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.*;

@Entity
public class PlayerItem extends AbstractEntity {

    @EmbeddedId
    PlayerItemKey playerItemKey;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    private String slot;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public PlayerItemKey getPlayerItemKey() {
        return playerItemKey;
    }

    public void setPlayerItemKey(PlayerItemKey playerItemKey) {
        this.playerItemKey = playerItemKey;
    }
}
