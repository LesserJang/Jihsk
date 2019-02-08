package com.jih10157.Jihsk.Condition;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import javax.annotation.Nullable;

public class CondHasAuthor extends Condition {
    static {
        Skript.registerCondition(CondHasAuthor.class, "book %itemstack% (1¦(ha(s[n[']t]|ve)|contain[s])|2¦(do[es](n't| not) have| do[es](n't| not) contain)) [had] [an] [book [meta]] author");
        Main.Condamount++;
    }
    private Expression<ItemStack> item;
    private Boolean boo = true;

    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.item = (Expression<ItemStack>)e[0];
        if (parser.mark == 2) {
            this.boo = false;
        }
        return true;
    }

    public String toString(@Nullable Event e, boolean arg1) { return "Jihsk CondHasAuthor"; }

    public boolean check(Event e) {
        final BookMeta book = (BookMeta) this.item.getSingle(e).getItemMeta();
        if (book.hasAuthor()) {
            return this.boo;
        }
        return !this.boo;
    }
}
