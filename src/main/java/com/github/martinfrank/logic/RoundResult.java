package com.github.martinfrank.logic;

import java.util.ArrayList;
import java.util.List;

public class RoundResult {

    private final CombatEntity player;
    private final CombatEntity opponent;
    private boolean isVictory;

    private List<String> messages = new ArrayList<>();

    public RoundResult(CombatEntity player, CombatEntity opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public void add(String message){
        messages.add(message);
    }

    public void setVictory(boolean b) {
        this.isVictory = true;
    }

    public boolean isVictory() {
        return isVictory;
    }

    public List<String> getMessages() {
        return messages;
    }
}
