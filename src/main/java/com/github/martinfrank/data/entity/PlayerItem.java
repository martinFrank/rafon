package com.github.martinfrank.data.entity;

import javax.persistence.*;

@Entity
public class PlayerItem {

//    @EmbeddedId
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Nonnull
//    private Integer id;

    @EmbeddedId
    PlayerItemKey id;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    Player player;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    Item item;

    String slot;

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
}
