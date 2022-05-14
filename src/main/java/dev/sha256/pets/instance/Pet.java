package dev.sha256.pets.instance;

import dev.sha256.pets.Pets;

public class Pet {

    private String name;
    private Integer level, xp, maxLevel;

    private Pets pets;


    public Pet(String name, Pets pets) {
        this.name = name;
        this.pets = pets;
    }


    //Setters

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    //Getters

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getXp() {
        return xp;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }
}
