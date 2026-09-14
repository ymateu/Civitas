package com.ymateu.civitas.events;

import com.ymateu.civitas.engine.GameState;

public class EventChoice {
    private final String description;
    private EventAction eventAction;

    public EventChoice(String description, EventAction eventAction) {
        this.description = description;
        this.eventAction = eventAction;
    }

    public String getDescription() {
        return description;
    }

    public void execute(GameState state) {
        eventAction.execute(state);
    }
}
