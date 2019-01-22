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
import javax.annotation.Nullable;

import com.jih10157.Jihsk.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class ExprCaughtItem extends SimpleExpression<ItemStack> {
    static {
        Skript.registerExpression(ExprCaughtItem.class, ItemStack.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]Caught Item");
        Main.Expramount++;
    }
    @Nullable
    @Override
    protected ItemStack[] get(Event e) { return new ItemStack[] { ((Item)(((PlayerFishEvent)e).getCaught())).getItemStack() }; }
    @Override
    public Class<? extends ItemStack> getReturnType() { return ItemStack.class; }
    @Override
    public boolean isSingle() { return true; }
    @Override
    public String toString(Event e, boolean debug) { return "Jihsk ExprCaughtItem"; }

    @Nullable
    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return (Class[])CollectionUtils.array(new Class[] { ItemStack.class });
        }
        return null;
    }
    @Override
    public void change(Event e, @Nullable Object[] ob, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            Entity entity = ((PlayerFishEvent)e).getCaught();
            if (entity == null) return;
            if (entity.getType().equals(EntityType.DROPPED_ITEM)) {
                assert ob != null;
                ((Item)entity).setItemStack((ItemStack)ob[0]);
            }
        }
    }
    @Override
    public boolean init(Expression<?>[] vars, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        if (!ScriptLoader.isCurrentEvent(PlayerFishEvent.class)) {
            Skript.error("Caught Item은 fishing 이벤트에서만 사용 가능합니다.", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
}
