package com.jih10157.Jihsk.Expression;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;

import javax.annotation.Nullable;

public class ExprFishingState extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprFishingState.class, String.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]Fishing State");
        Main.Expramount++;
    }
    @Override
    public Class<? extends String> getReturnType() { return String.class; }
    @Override
    public boolean isSingle(){ return true; }
    @Override
    public String toString(Event e, boolean debug) { return "Jihsk ExprFishingState"; }
    @Nullable
    @Override
    protected String[] get(Event e) { return new String[] { ((PlayerFishEvent)e).getState().toString() }; }
    @Override
    public boolean init(Expression<?>[] vars, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        if (!ScriptLoader.isCurrentEvent(PlayerFishEvent.class)) {
            Skript.error("Fishing State는 fishing 이벤트에서만 사용 가능합니다.", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
}
