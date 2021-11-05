package com.github.martinfrank.logic;

public class CombatEntity {

    private String name;
    private double life;
    private double endurance;// really?
    private Die attack;
    private Die damage;
    private Die defense;
    private Die armor;

    public CombatEntity(String name, double life, Die attack, Die damage, Die defense, Die armor){
        this.name = name;
        this.life = life;
        //private double endurance;// really?
        this.attack = attack;
        this.damage = damage;
        this.defense = defense;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public Die getAttack() {
        return attack;
    }

    public Die getDamage() {
        return damage;
    }

    public Die getDefense() {
        return defense;
    }

    public Die getArmor() {
        return armor;
    }

    public void dealDamage(int damageDealt) {
        life = life - damageDealt;
    }

    public boolean isDead(){
        return life <= 0;
    }

    public double getLife() {
        return life;
    }
}
