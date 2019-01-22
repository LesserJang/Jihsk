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
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExprLine extends SimpleExpression<String> {
    private Expression<ItemStack> item;
    private Expression<Number> linenum;
    static {
        Skript.registerExpression(ExprLine.class, String.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]variable (line of %-itemstack/itemtype%|%-itemstack/itemtype%'s line) [number %-number%]");
        Main.Expramount++;
    }
    @Nullable
    @Override
    protected String[] get(Event e) {
        if(linenum == null) {
            if(this.item == null) return null;
            ItemStack itemStack = this.item.getSingle(e);
            if(itemStack==null) return null;
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(itemMeta == null) return null;
            List<String> lores = itemMeta.getLore();
            if (lores == null) return null;
            return lores.toArray(new String[lores.size() + 1]);
        } else {
            if(linenum.getSingle(e).equals(0)) return null;
            if(this.item == null) return null;
            ItemStack itemStack = this.item.getSingle(e);
            if(itemStack==null) return null;
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(itemMeta == null) return null;
            List<String> lores = itemMeta.getLore();
            if(lores == null) return null;
            return new String[] { lores.get((linenum.getSingle(e).intValue())-1) };
        }
    }
    @Override
    public Class<? extends String> getReturnType() { return String.class; }
    @Override
    public boolean isSingle() { return true; }
    @Override
    public String toString(Event e, boolean debug) { return "Jihsk ExprLine"; }
    @Override
    public boolean init(Expression<?>[] vars, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser){
        this.item = (Expression<ItemStack>)((vars[0]==null) ? vars[1] : vars[0]);
        this.linenum = (Expression<Number>)vars[2];
        return true;
    }
    @Override
    @Nullable
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return (Class[])CollectionUtils.array(new Class[] { String.class });
        } else if(mode==Changer.ChangeMode.ADD) {
            return (Class[])CollectionUtils.array(new Class[] { String.class });
        } else if(mode==Changer.ChangeMode.REMOVE) {
            return (Class[])CollectionUtils.array(new Class[]{ String.class });
        } else if(mode==Changer.ChangeMode.DELETE) {
            return (Class[])CollectionUtils.array(new Class[]{ String.class });
        }
        return null;
    }
    @Override
    public void change(Event e, @Nullable Object[] ob, Changer.ChangeMode mode) {
        if(this.item==null) return;
        try { if (linenum.getSingle(e).equals(0)&&(!(mode==Changer.ChangeMode.ADD))) return;
        } catch(NullPointerException ignored) {}
        if(mode == Changer.ChangeMode.SET) {
            ItemStack itemStack = this.item.getSingle(e);
            if(itemStack==null) return;
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(itemMeta==null) return;
            if(linenum==null) {
                itemMeta.setLore(Arrays.asList((String[])ob));
                itemStack.setItemMeta(itemMeta);
                return;
            }
            int i = linenum.getSingle(e).intValue()-1;
            if(!itemMeta.hasLore()) {
                List<String> newlist = new ArrayList<>(i+2);
                for(int n=0;n<i;n++) newlist.add("");
                newlist.add((String)ob[0]);
                itemMeta.setLore(newlist);
                itemStack.setItemMeta(itemMeta);
                return;
            }
            List<String> lores = itemMeta.getLore();
            while(lores.size()<=i) lores.add("");
            lores.set(i, (String)ob[0]);
            itemMeta.setLore(lores);
            itemStack.setItemMeta(itemMeta);
        } else if(mode==Changer.ChangeMode.ADD) {
            ItemStack itemStack = this.item.getSingle(e);
            if(itemStack==null) return;
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(itemMeta==null) return;
            if(!itemMeta.hasLore()) {
                List<String> newlist = new ArrayList<>();
                newlist.add((String)ob[0]);
                itemMeta.setLore(newlist);
                itemStack.setItemMeta(itemMeta);
                return;
            }
            List<String> lores = itemMeta.getLore();
            lores.add((String)ob[0]);
            itemMeta.setLore(lores);
            itemStack.setItemMeta(itemMeta);
        } else if(mode==Changer.ChangeMode.REMOVE) {
            ItemStack itemStack = this.item.getSingle(e);
            if(itemStack==null) return;
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(itemMeta==null) return;
            if(!itemMeta.hasLore()) return;
            List<String> lores = itemMeta.getLore();
            lores.remove(ob[0]);
            itemMeta.setLore(lores);
            itemStack.setItemMeta(itemMeta);
        } else if(mode==Changer.ChangeMode.DELETE) {
            ItemStack itemStack = this.item.getSingle(e);
            if(itemStack==null) return;
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(itemMeta==null) return;
            if(!itemMeta.hasLore()) return;
            if(linenum==null) {
                List lores = itemMeta.getLore();
                lores.clear();
                itemMeta.setLore(lores);
                itemStack.setItemMeta(itemMeta);
                return;
            }
            List<String> lores = itemMeta.getLore();
            try { lores.remove(linenum.getSingle(e).intValue() - 1);
            } catch(IndexOutOfBoundsException ignored) {}
            itemMeta.setLore(lores);
            itemStack.setItemMeta(itemMeta);
        }
    }
}
