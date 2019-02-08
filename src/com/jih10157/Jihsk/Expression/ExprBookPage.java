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
import java.util.List;

public class ExprBookPage extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprBookPage.class, String.class, ExpressionType.SIMPLE, "[jih[sk].[ ]][the] [book['s]] page %number% (of|in) [book] %itemstack%", "[jih[sk].[ ]]%itemstack%'s [book] page %number%");
        Main.Expramount++;
    }
    private Expression<Number> page;
    private Expression<ItemStack> item;

    public Class<? extends String> getReturnType() { return String.class; }

    public boolean isSingle() { return true; }

    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        if (matchedPattern == 0) {
            this.page = (Expression<Number>)e[0];
            this.item = (Expression<ItemStack>)e[1];
        }
        else {
            this.item = (Expression<ItemStack>)e[0];
            this.page = (Expression<Number>)e[1];
        }
        return true;
    }

    public String toString(@Nullable Event e, boolean arg1) {
        return "Jihsk ExprBookPage";
    }

    @Nullable
    protected String[] get(Event e) {
        BookMeta book = (BookMeta) this.item.getSingle(e).getItemMeta();
        Number num = this.page.getSingle(e);
        return new String[] { book.getPage(num.intValue()) };
    }

    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        BookMeta book = (BookMeta) this.item.getSingle(e).getItemMeta();
        Number num = this.page.getSingle(e);
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD) {
            book.setPage(num.intValue(), (String)delta[0]);
        } else if (mode == Changer.ChangeMode.DELETE) {
            List<String> pages = book.getPages();
            pages.remove(num.intValue());
            book.setPages(pages);
        } else if (mode == Changer.ChangeMode.RESET) {
            List<String> pages = book.getPages();
            pages.set(num.intValue(), "");
            book.setPages(pages);
        }
        this.item.getSingle(e).setItemMeta(book);
    }

    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.ADD) {
            return (Class[])CollectionUtils.array(new Class[] { String.class });
        }
        return null;
    }
}
