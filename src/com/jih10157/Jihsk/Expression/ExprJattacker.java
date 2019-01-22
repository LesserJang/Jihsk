package com.jih10157.Jihsk.Expression;

import ch.njol.skript.lang.ExpressionType;
import com.jih10157.Jihsk.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExprJattacker extends SimpleExpression<Entity>{
    private static Method getShooter;
	static {
	    try {
	        getShooter = Projectile.class.getMethod("getShooter");
        } catch (Exception ignored) {}
        Skript.registerExpression(ExprJattacker.class, Entity.class, ExpressionType.SIMPLE, "[jih[sk].[ ]][the] (jattacker|jdamager)");
        Main.Expramount++;
	}
	@Override
	public boolean init(Expression<?>[] vars, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		if (!ScriptLoader.isCurrentEvent(EntityDamageByEntityEvent.class, EntityDeathEvent.class, VehicleDamageEvent.class, VehicleDestroyEvent.class)) {
			Skript.error("Cannot use 'jattacker' outside of a damage/death/destroy event", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return true;
	}

	@Override
	protected Entity[] get(Event e) { return new Entity[] { getAttacker(e) }; }
	private static Entity getAttacker(Event e) {
		if ((e instanceof EntityDamageByEntityEvent)) {
			if (((EntityDamageByEntityEvent)e).getDamager() instanceof Projectile) {
                try {
                    Object attacker = getShooter.invoke((((EntityDamageByEntityEvent)e).getDamager()));
                    if(attacker instanceof Entity) {
                        return (Entity)attacker;
                    }
                    return null;
                } catch (IllegalAccessException|InvocationTargetException ignored) { }
                return null;
			}
			return ((EntityDamageByEntityEvent)e).getDamager();
		}
		if ((e instanceof EntityDeathEvent))
			return getAttacker(((EntityDeathEvent)e).getEntity().getLastDamageCause());
		if ((e instanceof VehicleDamageEvent))
			return ((VehicleDamageEvent)e).getAttacker();
		if ((e instanceof VehicleDestroyEvent)) {
			return ((VehicleDestroyEvent)e).getAttacker();
		}
		return null;
	}
	@Override
	public Class<? extends Entity> getReturnType() { return Entity.class; }

	@Override
	public String toString(Event e, boolean debug) { return "Jihsk ExprJattacker"; }
	@Override
	public boolean isSingle() { return true; }
}