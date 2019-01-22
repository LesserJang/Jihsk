package com.jih10157.Jihsk.Votifier;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.jih10157.Jihsk.Main;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EvtVoteEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    static {
        Skript.registerEvent("Jihsk Votfiler Vote", SimpleEvent.class, EvtVoteEvent.class, "[jih[sk].[ ]]Any Vote");
        EventValues.registerEventValue(EvtVoteEvent.class, String.class, new Getter<String, EvtVoteEvent>() {
            @Override
            public String get(EvtVoteEvent evtVoteEvent) {
                return evtVoteEvent.getUsername();
            }
        }, 0);
        Main.Evtamount++;
    }
    private final String username;

    public EvtVoteEvent(String username) { this.username = username; }
    String getUsername() { return this.username; }
    @Override
    public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
