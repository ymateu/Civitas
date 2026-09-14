package com.ymateu.civitas.events;

import com.ymateu.civitas.engine.GameState;
import com.ymateu.civitas.events.event.ProtestEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {
    private final List<Event> events;
    private final Random random;

    public EventManager() {
        events = new ArrayList<>();
        random = new Random();

        registerEvents();
    }

    public void registerEvents() {
        new ProtestEvent();
    }

    public Event generateEvent(GameState state) {

        List<Event> availableEvents = events.stream()
                .filter(event -> event.canOccur(state))
                .toList();

        if (availableEvents.isEmpty()) {
            return null;
        }

        return availableEvents.get(random.nextInt(availableEvents.size()));
    }
}
