package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Combat extends AbstractEntity {

    @NotNull
    private String name;

    @NotEmpty
    @OneToOne
    private Opponent opponent;


    private double opponentLife;

    public Opponent getOpponent() {
        return opponent;
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

    public double getOpponentLife() {
        return opponentLife;
    }

    public void setOpponentLife(double opponentLife) {
        this.opponentLife = opponentLife;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
