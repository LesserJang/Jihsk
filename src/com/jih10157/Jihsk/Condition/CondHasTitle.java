package com.jih10157.Jihsk.Condition;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import javax.annotation.Nullable;

public class CondHasTitle extends Condition {

    private Expression<ItemStack> item;
    private Boolean boo = true;

    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.item = (Expression<ItemStack>)e[0];
        if (parser.mark == 2) {
            this.boo = false;
        }
        return true;
    }

    public String toString(@Nullable Event e, boolean arg1) { return "Jihsk CondHasTitle"; }

    public boolean check(Event e) {
        BookMeta book = (BookMeta) this.item.getSingle(e).getItemMeta();
        if (book.hasTitle()) { return this.boo; }
        return !this.boo;
    }
}
