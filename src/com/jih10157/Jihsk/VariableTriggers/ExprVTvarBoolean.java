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
import com.wizardscraft.VariableTriggers.VariableTriggers;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprVTvarBoolean extends SimpleExpression<Boolean> {
    static {
        Skript.registerExpression(ExprVTvarBoolean.class, Boolean.class, ExpressionType.SIMPLE, "[jih[sk].](vt|variabletriggers) (boolean|b) object name %string% and var name %string%");
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
    protected Boolean[] get(Event e) {
        return new Boolean[] { Main.vt.varData.getObjectBooleanData(this.objectname.getSingle(e), this.varname.getSingle(e)) };
    }
    @Override
    public void change(Event e, @Nullable Object[] ob, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            assert ob != null;
            Main.vardata.setObjectBooleanData(this.objectname.getSingle(e), this.varname.getSingle(e), Boolean.valueOf(String.valueOf(ob[0])));
        } else if (mode == Changer.ChangeMode.DELETE) {
            Main.vardata.delObjectBooleanData(this.objectname.getSingle(e), this.varname.getSingle(e));
        }
    }
    @Override
    @Nullable
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if ((mode == Changer.ChangeMode.SET) || (mode == Changer.ChangeMode.DELETE)) {
            return (Class[])CollectionUtils.array(new Class[] { Boolean.class });
        }
        return null;
    }

    @Override
    public Class<? extends Boolean> getReturnType() { return Boolean.class; }

    @Override
    public String toString(Event e, boolean debug) { return "Jihsk ExprVTvarBoolean"; }

    @Override
    public boolean isSingle() { return true; }
}