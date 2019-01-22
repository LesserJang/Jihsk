package com.jih10157.Jihsk.Votifier;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.jih10157.Jihsk.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EvtOnlineVoteEvent extends SkriptEvent {
    static {
        Skript.registerEvent("Jihsk EvtOnlineVoteEvent", EvtOnlineVoteEvent.class, EvtVoteEvent.class, "[jih[sk].[ ]][Online] Vote");
        EventValues.registerEventValue(EvtVoteEvent.class, Player.class, new Getter<Player, EvtVoteEvent>() {
            @Override
            public Player get(EvtVoteEvent evtVoteEvent) {
                return Bukkit.getServer().getPlayerExact(evtVoteEvent.getUsername());
            }
        }, 0);
        Main.Evtamount++;
    }
    @Override
    public String toString(@Nullable Event event, boolean Boolean){ return "Jihsk EvtOnlineVoteEvent"; }
    @Override
    public boolean check(Event event) {
        return !((Bukkit.getServer().getPlayerExact(((EvtVoteEvent)event).getUsername())) == null);
    }
    @Override
    public boolean init(Literal<?>[] arg0, int arg1, SkriptParser.ParseResult arg2) { return true; }
}
