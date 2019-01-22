package com.jih10157.Jihsk.Expression;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.event.Event;

import java.util.ArrayList;

public class ExprFind extends SimpleExpression<Number> {
    static {
        Skript.registerExpression(ExprFind.class, Number.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]find %object% with %objects%");
        Main.Expramount++;
    }
    private Expression<Object> object1;
    private Expression<Object> object2;
    @Override
    protected Number[] get(Event event) {
        if(object1==null||object2==null) { return null; }
        Object[] objectlist = object2.getAll(event);
        int i = 0;
        ArrayList<Number> list = new ArrayList<>();
        for(Object obj : objectlist) {
            if(obj.equals(object1.getSingle(event))) {
                list.add(i+1);
            }
            i++;
        }
        return list.toArray(new Number[0]);
    }
    @Override
    public String toString(Event e, boolean debug) { return "Jihsk ExprFind"; }
    @Override
    public Class<? extends Number> getReturnType() { return Number.class; }
    @Override
    public boolean isSingle() { return true; }
    @Override
    public boolean init(Expression<?>[] vars, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        object1 = (Expression<Object>) vars[0];
        object2 = (Expression<Object>) vars[1];
        return true;
    }

}
