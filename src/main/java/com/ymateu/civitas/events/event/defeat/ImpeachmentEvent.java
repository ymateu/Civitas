package com.ymateu.civitas.events.event.defeat;

import com.ymateu.civitas.engine.GameState;
import com.ymateu.civitas.events.Event;
import com.ymateu.civitas.events.EventChoice;

public class ImpeachmentEvent extends Event{
        public ImpeachmentEvent() {
            super(
                    "Sua aprovacao chegou a zero",
                    "O povo se revoltou e pediu seu Impeachment. O STF julgou e condenou voce a prisao"
            );
        }

        @Override
        public boolean canOccur(GameState state) {
            return state.getSociety().getGovernmentApproval() <= 0;
        }

        @Override
        public EventChoice[] getChoices() {
            return new EventChoice[] {
                    new EventChoice(
                            "Iniciar novo jogo",
                            state -> {
                                state.getPresident().setPopularity(0);
                            }
                    )
            };
        }

}
