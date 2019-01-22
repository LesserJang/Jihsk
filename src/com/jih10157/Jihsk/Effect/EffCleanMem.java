package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffCleanMem extends Effect {
    static {
        Skript.registerEffect(EffCleanMem.class, "[jih[sk].[ ]]clean mem[ory]");
        Main.Effamount++;
    }
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) { return true; }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) { return "Jihsk EffCleanMem"; }

    @Override
    protected void execute(Event arg0) { System.gc(); }

}
