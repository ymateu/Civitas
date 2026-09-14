package com.ymateu.civitas.government;

public class President {
    private int popularity;

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void increasePopularity(int amount) {
        popularity += amount;
    }

    public void decreasePopularity(int amount) {
        popularity -= amount;
    }
}
