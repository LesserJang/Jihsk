package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

public class EffMakeDamage extends Effect {
    static {
        Skript.registerEffect(EffMakeDamage.class, "[jih[sk].[ ]]make %entity% damage %livingentity% by %number%");
        Main.Effamount++;
    }
    private Expression<Entity> le1;
    private Expression<LivingEntity> le2;
    private Expression<Number> damage;
    @Override
    protected void execute(Event event) {
        le2.getSingle(event).damage(damage.getSingle(event).intValue(), le1.getSingle(event));
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Jihsk EffMakeDamage";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.le1 = (Expression<Entity>)expressions[0];
        this.le2 = (Expression<LivingEntity>)expressions[1];
        this.damage = (Expression<Number>)expressions[2];
        return true;
    }
}
