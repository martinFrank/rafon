package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item extends AbstractEntity {

    @NotEmpty
    private String name = "";

    @OneToMany(mappedBy = "item")
    private Set<PlayerItem> playerItems = new HashSet<>();

    private String slot; //primary secondary, offhand, potion, food

    private String damage;

    private String attack;

    private String armor;

    private String defense;

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

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", slot='" + slot + '\'' +
                ", damage='" + damage + '\'' +
                ", attack='" + attack + '\'' +
                ", armor='" + armor + '\'' +
                ", defense='" + defense + '\'' +
                '}';
    }
}
