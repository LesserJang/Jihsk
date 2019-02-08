package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import javax.annotation.Nullable;

public class EffAddPage extends Effect {
    static {
        Skript.registerEffect(EffAddPage.class, "[jih[sk].[ ]]add [a] page [with] [(text|data)] [%-string%] to [book] %itemstack%");
    }
    private Expression<String> string;
    private Expression<ItemStack> book;

    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.string = (Expression<String>)e[0];
        this.book = (Expression<ItemStack>)e[1];
        return true;
    }

    public String toString(@Nullable Event paramEvent, boolean paramBoolean) { return "Jihsk EffAddPage"; }

    protected void execute(Event e) {
        if (this.book == null) { return; }
        if (this.book.getSingle(e).getType() == Material.BOOK_AND_QUILL || this.book.getSingle(e).getType() == Material.WRITTEN_BOOK) {
            BookMeta bookMeta = (BookMeta) this.book.getSingle(e).getItemMeta();
            if (this.string != null) { bookMeta.addPage(this.string.getSingle(e)); }
            else { bookMeta.addPage(); }
            this.book.getSingle(e).setItemMeta(bookMeta);
        }
    }
}
