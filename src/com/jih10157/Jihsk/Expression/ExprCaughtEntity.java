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
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;

import javax.annotation.Nullable;

public class ExprCaughtEntity extends SimpleExpression<Entity> {
    static {
        Skript.registerExpression(ExprCaughtEntity.class, Entity.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]Caught Entity");
        Main.Expramount++;
    }
    @Nullable
    @Override
    protected Entity[] get(Event e) { return new Entity[] { ((PlayerFishEvent)e).getCaught() }; }
    @Override
    public Class<? extends Entity> getReturnType() { return Entity.class; }
    @Override
    public boolean isSingle() { return true; }
    @Override
    public String toString(Event e, boolean debug) { return "Jihsk ExprCaughtEntity"; }

    @Override
    public boolean init(Expression<?>[] vars, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        if (!ScriptLoader.isCurrentEvent(PlayerFishEvent.class)) {
            Skript.error("caught entity는 fishing 이벤트에서만 사용 가능합니다.", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
}
