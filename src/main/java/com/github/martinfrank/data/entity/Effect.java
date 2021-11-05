package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Effect extends AbstractEntity {

    @NotEmpty
    private String name = "";

    @OneToMany(mappedBy = "effect")
    private Set<PlayerEffect> playerEffect = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlayerEffect> getPlayerEffect() {
        return playerEffect;
    }

    public void setPlayerEffect(Set<PlayerEffect> playerEffect) {
        this.playerEffect = playerEffect;
    }
}
