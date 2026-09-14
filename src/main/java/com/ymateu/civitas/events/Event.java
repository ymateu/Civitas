package com.ymateu.civitas.events;

import com.ymateu.civitas.engine.GameState;

public abstract class Event {
    private final String title;
    private final String description;

    public Event(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public abstract EventChoice[] getChoices();
    public abstract boolean canOccur(GameState state);
}
