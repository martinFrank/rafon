package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Combat extends AbstractEntity {

    @NotNull
    private String name;

    private int round;

    private long seed;

    @NotNull
    @OneToOne
    @JoinColumn(name = "opponent_id")
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

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
}
