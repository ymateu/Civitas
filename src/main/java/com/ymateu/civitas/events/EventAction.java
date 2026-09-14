package com.ymateu.civitas.events;

import com.ymateu.civitas.engine.GameState;

public interface EventAction {

    public void execute(GameState state);
}
