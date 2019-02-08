package com.jih10157.Jihsk.Expression;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.jih10157.Jihsk.Main;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import javax.annotation.Nullable;

public class ExprBookAuthor extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprBookAuthor.class, String.class, ExpressionType.SIMPLE, "[jih[sk].[ ]][the] [book['s]] author of %itemstack%", "[jih[sk].[ ]]%itemstack%'s [book] author");
        Main.Expramount++;
    }
    private Expression<ItemStack> item;

    public Class<? extends String> getReturnType() { return String.class; }

    public boolean isSingle() { return true; }

    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.item = (Expression<ItemStack>)e[0];
        return true;
    }

    public String toString(@Nullable Event e, boolean arg1) { return "Jihsk ExprBookAuthor"; }

    @Nullable
    protected String[] get(Event e) {
        BookMeta book = (BookMeta)this.item.getSingle(e).getItemMeta();
        return new String[] { book.getAuthor() };
    }

    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        BookMeta book = (BookMeta)this.item.getSingle(e).getItemMeta();
        if (mode == Changer.ChangeMode.SET) {
            book.setAuthor((String)delta[0]);
        }
        this.item.getSingle(e).setItemMeta(book);
    }

    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return (Class[])CollectionUtils.array(new Class[] { String.class });
        }
        return null;
    }
}
