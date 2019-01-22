package com.jih10157.Jihsk.Effect;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;

import javax.annotation.Nullable;

public class EffResetHitCooldown extends Effect {

    static {
        Skript.registerEffect(EffResetHitCooldown.class, "[jih[sk].[ ]]Reset Hit Cooldown");
        Main.Effamount++;
    }

    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (!ScriptLoader.isCurrentEvent(EntityDamageByEntityEvent.class, VehicleDamageEvent.class)) {
            Skript.error("Reset Hit Cooldown은 데미지문에서만 가능합니다.", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) { return "Jihsk EffResetHitCooldown"; }

    @Override
    protected void execute(Event e) {
        if ((e instanceof EntityDamageByEntityEvent)) {
            Entity entvictim = ((EntityDamageByEntityEvent) e).getEntity();
            if (entvictim instanceof LivingEntity) {
                ((LivingEntity) entvictim).setNoDamageTicks(0);
            }
        }
        if ((e instanceof EntityDamageEvent)) {
            Entity entvictim = ((EntityDamageEvent) e).getEntity();
            if (entvictim instanceof LivingEntity) {
                ((LivingEntity) entvictim).setNoDamageTicks(0);
            }
        }
    }
}