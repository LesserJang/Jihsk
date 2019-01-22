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
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;

import javax.annotation.Nullable;

public class ExprFishingExp extends SimpleExpression<Integer> {
    static {
        Skript.registerExpression(ExprFishingExp.class, Integer.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]Fishing Exp");
        Main.Expramount++;
    }
    @Override
    public Class<? extends Integer> getReturnType() { return Integer.class; }
    @Override
    public boolean isSingle() { return true; }
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (!ScriptLoader.isCurrentEvent(PlayerFishEvent.class)) {
            Skript.error("Fishing Exp는 fishing 이벤트에서만 사용 가능합니다.", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) { return "Jihsk ExprFishingExp"; }
    @Override
    protected Integer[] get(Event e) {
        return new Integer[] { ((PlayerFishEvent)e).getExpToDrop() };
    }
    @Nullable
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return (Class[])CollectionUtils.array(new Class[] { Integer.class });
        }
        return null;
    }
    @Override
    public void change(Event e, @Nullable Object[] ob, Changer.ChangeMode mode) {
        if(mode == Changer.ChangeMode.SET) {
            assert ob != null;
            ((PlayerFishEvent)e).setExpToDrop((Integer)ob[0]);
        }
    }
}