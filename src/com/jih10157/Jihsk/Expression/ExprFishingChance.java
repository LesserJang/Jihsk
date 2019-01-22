package com.jih10157.Jihsk.Expression;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.jih10157.Jihsk.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;

import javax.annotation.Nullable;

public class ExprFishingChance extends SimpleExpression<Number> {
    private static boolean ver;
    static {
        Skript.registerExpression(ExprFishingChance.class, Number.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]Fishing Chance");
        Main.Expramount++;
    }
    @Override
    public Class<? extends Number> getReturnType() { return Number.class; }
    @Override
    public boolean isSingle() { return true; }
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (!ScriptLoader.isCurrentEvent(PlayerFishEvent.class)) {
            Skript.error("Fishing Chance는 fishing 이벤트에서만 사용 가능합니다.", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) { return "Jihsk ExprFishingChance"; }
    @Override
    protected Number[] get(Event e) { return new Number[] { ((PlayerFishEvent)e).getHook().getBiteChance() }; }
    @Override
    @Nullable
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return (Class[])CollectionUtils.array(new Class[] { Number.class });
        }
        return null;
    }
    @Override
    public void change(Event e, @Nullable Object[] ob, Changer.ChangeMode mode) {
        if(mode == Changer.ChangeMode.SET) {
            ((PlayerFishEvent)e).getHook().setBiteChance(((Number)ob[0]).doubleValue());
        }
    }
}