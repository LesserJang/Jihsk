package com.jih10157.Jihsk.Expression;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class ExprNewBook extends SimpleExpression<ItemStack> {
    static {
        Skript.registerExpression(ExprNewBook.class, ItemStack.class, ExpressionType.SIMPLE, "[jih[sk].[ ]][a] new [written] book");
        Main.Expramount++;
    }

    public Class<? extends ItemStack> getReturnType() { return ItemStack.class; }

    public boolean isSingle() { return true; }

    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) { return true; }

    public String toString(@Nullable Event e, boolean arg1) { return "Jihsk ExprNewBook"; }

    @Nullable
    protected ItemStack[] get(Event e) { return new ItemStack[] { new ItemStack(Material.WRITTEN_BOOK, 1) }; }
}