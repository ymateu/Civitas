package com.ymateu.civitas.events.event.society;

import com.ymateu.civitas.engine.GameState;
import com.ymateu.civitas.events.Event;
import com.ymateu.civitas.events.EventChoice;

public class ProtestEvent extends Event {

    public ProtestEvent() {
        super(
                "Um protesto se iniciou",
                "Manifestantes estao na praca querendo sua cabeca"
        );
    }

    @Override
    public boolean canOccur(GameState state) {
        return state.getSociety().getGovernmentApproval() < 30;
    }

    @Override
    public EventChoice[] getChoices() {
        return new EventChoice[] {
                new EventChoice(
                        "Negociar com os manifestantes",
                        state -> {
                            state.getPresident().increasePopularity(10);
                        }
                ),

                new EventChoice(
                        "Bater nos manifestantes",
                        state -> {
                            state.getPresident().decreasePopularity(20);
                        }
                )
        };
    }
}
