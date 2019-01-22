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
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class ExprHopperPickupItemEntity extends SimpleExpression<Entity> {
    static {
        Skript.registerExpression(ExprHopperPickupItemEntity.class, Entity.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]Pickup Item [Entity]");
        Main.Expramount++;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(new Class[] { InventoryPickupItemEvent.class })) {
            Skript.error("Pickup Item은 Hopper Pickup 이벤트에서만 사용 가능합니다.", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
    @Override
    public String toString(Event event, boolean b) { return "Jihsk ExprHopperPickupItemEntity"; }
    @Override
    public boolean isSingle() { return true; }
    @Override
    public Class<? extends Entity> getReturnType() { return Entity.class; }
    @Override
    protected Entity[] get(Event event) { return new Entity[] { ((InventoryPickupItemEvent)event).getItem() }; }
}
