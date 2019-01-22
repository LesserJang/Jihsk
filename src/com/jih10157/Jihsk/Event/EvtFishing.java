package com.jih10157.Jihsk.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.jih10157.Jihsk.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;

public class EvtFishing {
    static {
        Skript.registerEvent("jih.Fishing", SimpleEvent.class, PlayerFishEvent.class, "[jih[sk].[ ]]fishing");
        EventValues.registerEventValue(PlayerFishEvent.class, Player.class, new Getter<Player, PlayerFishEvent>() {
            @Override
            public Player get(PlayerFishEvent playerFishEvent) {
                return playerFishEvent.getPlayer();
            }
        }, 0);
        Main.Evtamount++;
    }
}
