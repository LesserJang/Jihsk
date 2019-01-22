package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Color;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.event.Event;
import org.bukkit.inventory.meta.FireworkMeta;

import javax.annotation.Nullable;

public class EffLaunchFirework extends Effect {
    static {
        Skript.registerEffect(EffLaunchFirework.class, "[jih[sk].[ ]]Launch Firework Location %location% Type (0¦BALL|1¦BALL_LARGE|2¦STAR|3¦BURST|4¦CREEPER) Flicker %boolean% Trail %boolean% Color %colors% Fade %colors% Power %number%");
        Main.Effamount++;
    }
    private Expression<Location> loc;
    private Expression<Boolean> flicker;
    private Expression<Boolean> trail;
    private Expression<Color> color;
    private Expression<Color> fade;
    private Expression<Number> power;
    private int caesium;

    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.caesium = arg3.mark;
        this.loc = (Expression<Location>)arg0[0];
        this.flicker = (Expression<Boolean>)arg0[1];
        this.trail = (Expression<Boolean>)arg0[2];
        this.color = (Expression<Color>)arg0[3];
        this.fade = (Expression<Color>)arg0[4];
        this.power = (Expression<Number>)arg0[5];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) { return "Jihsk EffLaunchFirework"; }

    @Override
    protected void execute(Event e) {
        FireworkEffect.Type type = FireworkEffect.Type.values()[this.caesium];
        Color[] colors = this.color.getAll(e);
        org.bukkit.Color[] buckaroos1 = new org.bukkit.Color[colors.length];
        for (int i=0; i < colors.length; i++) buckaroos1[i] = colors[i].getBukkitColor();
        Color[] fade = this.fade.getAll(e);
        org.bukkit.Color[] buckaroos2 = new org.bukkit.Color[fade.length];
        for (int i=0; i < fade.length; i++) buckaroos2[i] = fade[i].getBukkitColor();
        Firework fw = (this.loc.getSingle(e)).getWorld().spawn(this.loc.getSingle(e), Firework.class);
        FireworkMeta fm = fw.getFireworkMeta();
        fm.setPower((this.power.getSingle(e)).intValue());
        FireworkEffect effect = FireworkEffect.builder().trail(this.trail.getSingle(e)).flicker(this.flicker.getSingle(e)).with(type).withColor(buckaroos1).withFade(buckaroos2).build();
        fm.addEffect(effect);
        fw.setFireworkMeta(fm);
    }
}