package com.ymateu.civitas.engine;

import com.ymateu.civitas.government.President;
import com.ymateu.civitas.society.Society;

public class GameState {
    private President president;
    private Society society;

    private int bimonthly;
    private int year;

    private String name;

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public President getPresident() {
        return president;
    }

    public void setPresident(President president) {
        this.president = president;
    }

    public int getBimonthly() {
        return bimonthly;
    }

    public void setBimonthly(int bimonthly) {
        this.bimonthly = bimonthly;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
