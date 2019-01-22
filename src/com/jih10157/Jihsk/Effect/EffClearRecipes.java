package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public class EffClearRecipes extends Effect {
    static {
        Skript.registerEffect(EffClearRecipes.class, "[jih[sk].[ ]]clear recipes");
        Main.Effamount++;
    }
    @Override
    protected void execute(Event event) { Bukkit.getServer().clearRecipes(); }
    @Override
    public String toString(Event event, boolean b) { return "Jihsk EffClearRecipes"; }
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) { return true; }
}
