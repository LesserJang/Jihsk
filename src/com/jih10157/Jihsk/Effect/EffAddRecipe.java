package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class EffAddRecipe  extends Effect
{
    static {
        Skript.registerEffect(EffAddRecipe.class, "[jih[sk].[ ]]add recipe to %itemtype% using %itemtype%, %itemtype%, %itemtype%, %itemtype%, %itemtype%, %itemtype%, %itemtype%, %itemtype%, %itemtype%");
        Main.Effamount++;
    }
    private boolean isShapeless;
    private Expression<ItemType> out;
    private Expression<ItemType> m1;
    private Expression<ItemType> m2;
    private Expression<ItemType> m3;
    private Expression<ItemType> m4;
    private Expression<ItemType> m5;
    private Expression<ItemType> m6;
    private Expression<ItemType> m7;
    private Expression<ItemType> m8;
    private Expression<ItemType> m9;

    @Override
    protected void execute(Event event) {
        ItemType o = this.out.getSingle(event);
        ItemType i1 = this.m1.getSingle(event);
        ItemType i2 = this.m2.getSingle(event);
        ItemType i3 = this.m3.getSingle(event);
        ItemType i4 = this.m4.getSingle(event);
        ItemType i5 = this.m5.getSingle(event);
        ItemType i6 = this.m6.getSingle(event);
        ItemType i7 = this.m7.getSingle(event);
        ItemType i8 = this.m8.getSingle(event);
        ItemType i9 = this.m9.getSingle(event);
        if ((o == null) || (i1 == null) || (i2 == null) || (i3 == null) || (i4 == null) || (i5 == null) || (i6 == null) || (i7 == null) || (i8 == null) || (i9 == null)) return;
        if (this.isShapeless) {
            ShapelessRecipe r = new ShapelessRecipe(o.getRandom());
            if (i1.getRandom().getType() != Material.AIR) r.addIngredient(i1.getRandom().getData());
            if (i2.getRandom().getType() != Material.AIR) r.addIngredient(i2.getRandom().getData());
            if (i3.getRandom().getType() != Material.AIR) r.addIngredient(i3.getRandom().getData());
            if (i4.getRandom().getType() != Material.AIR) r.addIngredient(i4.getRandom().getData());
            if (i5.getRandom().getType() != Material.AIR) r.addIngredient(i5.getRandom().getData());
            if (i6.getRandom().getType() != Material.AIR) r.addIngredient(i6.getRandom().getData());
            if (i7.getRandom().getType() != Material.AIR) r.addIngredient(i7.getRandom().getData());
            if (i8.getRandom().getType() != Material.AIR) r.addIngredient(i8.getRandom().getData());
            if (i9.getRandom().getType() != Material.AIR) r.addIngredient(i9.getRandom().getData());
            Skript.getInstance().getServer().addRecipe(r);
        } else {
            ShapedRecipe r = new ShapedRecipe(o.getRandom());
            r.shape("abc",
                    "def",
                    "ghi");
            if (i1.getRandom().getType() != Material.AIR) r.setIngredient('a', i1.getRandom().getData());
            if (i2.getRandom().getType() != Material.AIR) r.setIngredient('b', i2.getRandom().getData());
            if (i3.getRandom().getType() != Material.AIR) r.setIngredient('c', i3.getRandom().getData());
            if (i4.getRandom().getType() != Material.AIR) r.setIngredient('d', i4.getRandom().getData());
            if (i5.getRandom().getType() != Material.AIR) r.setIngredient('e', i5.getRandom().getData());
            if (i6.getRandom().getType() != Material.AIR) r.setIngredient('f', i6.getRandom().getData());
            if (i7.getRandom().getType() != Material.AIR) r.setIngredient('g', i7.getRandom().getData());
            if (i8.getRandom().getType() != Material.AIR) r.setIngredient('h', i8.getRandom().getData());
            if (i9.getRandom().getType() != Material.AIR) r.setIngredient('i', i9.getRandom().getData());
            Skript.getInstance().getServer().addRecipe(r);
        }
    }

    @Override
    public String toString(Event event, boolean b) { return "Jihsk EffAddRecipe"; }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        this.out = (Expression<ItemType>)expressions[0];
        this.m1 = (Expression<ItemType>)expressions[1];
        this.m2 = (Expression<ItemType>)expressions[2];
        this.m3 = (Expression<ItemType>)expressions[3];
        this.m4 = (Expression<ItemType>)expressions[4];
        this.m5 = (Expression<ItemType>)expressions[5];
        this.m6 = (Expression<ItemType>)expressions[6];
        this.m7 = (Expression<ItemType>)expressions[7];
        this.m8 = (Expression<ItemType>)expressions[8];
        this.m9 = (Expression<ItemType>)expressions[9];
        this.isShapeless = (i == 1);
        return true;
    }
}