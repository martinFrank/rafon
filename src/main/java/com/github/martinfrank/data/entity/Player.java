package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Player extends AbstractEntity {

    @NotEmpty
    private String displayName = "";

    @NotNull
    @OneToOne
    private User user;

    @NotNull
    @OneToOne
    private MapArea  currentArea;

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
