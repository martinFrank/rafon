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

    @NotNull
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @OneToOne
    private MapArea  currentArea;

    @OneToMany(mappedBy="player")
    private Set<QuestItem> items;

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
}
