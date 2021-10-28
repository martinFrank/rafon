package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Player extends AbstractEntity {

    @NotEmpty
    private String displayName = "";

    private Long currentLife;
    private Long maxLife;

    private Long currentEndurance;
    private Long maxEndurance;

    @OneToOne
    @JoinColumn(name = "combat_id", nullable = true)
    private Combat combat;


    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @OneToOne
    private MapArea currentArea;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_quest_item",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "quest_item_id"))
    private Set<QuestItem> questItems;

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

    public Long getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(Long currentLife) {
        this.currentLife = currentLife;
    }

    public Long getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(Long maxLife) {
        this.maxLife = maxLife;
    }

    public Long getCurrentEndurance() {
        return currentEndurance;
    }

    public void setCurrentEndurance(Long currentEndurance) {
        this.currentEndurance = currentEndurance;
    }

    public Long getMaxEndurance() {
        return maxEndurance;
    }

    public void setMaxEndurance(Long maxEndurance) {
        this.maxEndurance = maxEndurance;
    }

    public void setCombat(Combat combat) {
        this.combat = combat;
    }
}
