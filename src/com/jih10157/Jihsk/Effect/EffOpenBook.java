package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import com.jih10157.Jihsk.util.InGame.ForceOpenBook;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class EffOpenBook extends Effect {
    static {
        Skript.registerEffect(EffOpenBook.class, "[jih[sk].[ ]]open book %itemstack% to %player%");
        Main.Effamount++;
    }
    private Expression<ItemStack> book;
    private Expression<Player> player;

    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.book = (Expression<ItemStack>)e[0];
        this.player = (Expression<Player>)e[1];
        return true;
    }

    public String toString(@Nullable Event paramEvent, boolean paramBoolean) { return "Jihsk EffOpenBook"; }

    @Override
    protected void execute(Event event) {
        ForceOpenBook.openBook(this.player.getSingle(event), this.book.getSingle(event));
    }
}
