package com.jih10157.Jihsk.VariableTriggers;

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

import javax.annotation.Nullable;

public class ExprVTvarInt extends SimpleExpression<Integer> {
    static {
        Skript.registerExpression(ExprVTvarInt.class, Integer.class, ExpressionType.SIMPLE, "[jih[sk].](vt|variabletriggers) (int|i) object name %string% and var name %string%");
        Main.Expramount++;
    }

    private Expression<String> objectname;
    private Expression<String> varname;

    @Override
    public boolean init(Expression<?>[] vars, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.objectname = (Expression<String>)vars[0];
        this.varname = (Expression<String>)vars[1];
        return true;
    }

    @Override
    protected Integer[] get(Event e) {
        return new Integer[] { Main.vt.varData.getObjectIntData(this.objectname.getSingle(e), this.varname.getSingle(e)) };
    }
    @Override
    public void change(Event e, @Nullable Object[] ob, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            assert ob != null;
            Main.vardata.setObjectIntData(this.objectname.getSingle(e), this.varname.getSingle(e), Integer.parseInt(ob[0].toString()));
        } else if (mode == Changer.ChangeMode.ADD) {
            assert ob != null;
            Main.vardata.addObjectIntData(this.objectname.getSingle(e), this.varname.getSingle(e), Integer.parseInt(ob[0].toString()));
        } else if (mode == Changer.ChangeMode.REMOVE) {
            assert ob != null;
            Main.vardata.subObjectIntData(this.objectname.getSingle(e), this.varname.getSingle(e), Integer.parseInt(ob[0].toString()));
        } else if (mode == Changer.ChangeMode.DELETE) {
            Main.vardata.delObjectIntData(this.objectname.getSingle(e), this.varname.getSingle(e));
        }
    }
    @Override
    @Nullable
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if ((mode == Changer.ChangeMode.SET) || (mode == Changer.ChangeMode.ADD) || (mode == Changer.ChangeMode.REMOVE) || (mode == Changer.ChangeMode.DELETE)) {
            return (Class[])CollectionUtils.array(new Class[] { Number.class });
        }
        return null;
    }

    @Override
    public Class<? extends Integer> getReturnType() { return Integer.class; }

    @Override
    public String toString(Event e, boolean debug) { return "Jihsk ExprVTvarInt"; }

    @Override
    public boolean isSingle() { return true; }
}