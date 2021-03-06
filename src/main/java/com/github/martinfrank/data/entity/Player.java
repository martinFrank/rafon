package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player extends AbstractEntity {

    @NotEmpty
    private String displayName = "";

    private double currentLife;
    private double maxLife;

    private double currentEndurance;
    private double maxEndurance;

    @OneToOne
    @JoinColumn(name = "combat_id")
    private Combat combat;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @OneToOne
    private MapArea currentArea;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_quest_item",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "quest_item_id"))
    private Set<QuestItem> questItems = new HashSet<>();;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<PlayerItem> playerItems = new HashSet<>();

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<PlayerEffect> playerEffect = new HashSet<>();

    private double intelligence;
    private double dexterity;
    private double strength;

    public Combat getCombat() {
        return combat;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MapArea getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(MapArea currentArea) {
        this.currentArea = currentArea;
    }

    public Set<QuestItem> getQuestItems() {
        return questItems;
    }

    public void setQuestItems(Set<QuestItem> questItems) {
        this.questItems = questItems;
    }

    public double getCurrentLife() {
        return currentLife;
    }


    public double getMaxLife() {
        return maxLife;
    }


    public double getCurrentEndurance() {
        return currentEndurance;
    }

    public double getMaxEndurance() {
        return maxEndurance;
    }

    public void setCombat(Combat combat) {
        this.combat = combat;
    }

    public void setCurrentLife(double currentLife) {
        this.currentLife = currentLife;
    }

    public void setMaxLife(double maxLife) {
        this.maxLife = maxLife;
    }

    public void setCurrentEndurance(double currentEndurance) {
        this.currentEndurance = currentEndurance;
    }

    public void setMaxEndurance(double maxEndurance) {
        this.maxEndurance = maxEndurance;
    }

    public Set<PlayerItem> getPlayerItems() {
        return playerItems;
    }

    public void setPlayerItems(Set<PlayerItem> playerItems) {
        this.playerItems = playerItems;
    }

    public Set<PlayerEffect> getPlayerEffect() {
        return playerEffect;
    }

    public void setPlayerEffect(Set<PlayerEffect> playerEffect) {
        this.playerEffect = playerEffect;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
}
