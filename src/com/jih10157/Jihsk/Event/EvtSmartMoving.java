package com.jih10157.Jihsk.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.jih10157.Jihsk.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EvtSmartMoving extends Event{

    static {
        Skript.registerEvent("jih.SmartMoving", SimpleEvent.class, EvtSmartMoving.class, "[jih[sk].[ ]](smart|smart moving)");
        EventValues.registerEventValue(EvtSmartMoving.class, Player.class, new Getter<Player, EvtSmartMoving>() {
            @Override
            public Player get(EvtSmartMoving evtSmartMoving) {
                return evtSmartMoving.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(EvtSmartMoving.class, String.class, new Getter<String, EvtSmartMoving>() {
            @Override
            public String get(EvtSmartMoving evtSmartMoving) {
                return evtSmartMoving.getString();
            }
        }, 0);
        Main.Evtamount++;
    }
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private final String string;

    public EvtSmartMoving(Player player, String string) {
        this.player = player;
        this.string = string;
    }

    public Player getPlayer() { return player; }
    public String getString() { return string; }

    @Override
    public HandlerList getHandlers() { return HANDLER_LIST; }

    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
