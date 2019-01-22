package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffResetRecipes  extends Effect
{
    static {
        Skript.registerEffect(EffResetRecipes.class, "[jih[sk].[ ]]reset recipes");
        Main.Effamount++;
    }
    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parse) { return true; }

    @Override
    public String toString(@Nullable Event e, boolean b) { return "Jihsk EffResetRecipes"; }

    @Override
    protected void execute(Event e) { Bukkit.resetRecipes(); }
}