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

public class ExprVTvarString extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprVTvarString.class, String.class, ExpressionType.SIMPLE, "[jih[sk].](vt|variabletriggers) (string|s) object name %string% and var name %string%");
        Main.Expramount++;
    }
    private Expression<String> objectname;
    private Expression<String> varname;
    @Override
    public boolean init(Expression<?>[] vars, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser)
    {
        this.objectname = (Expression<String>)vars[0];
        this.varname = (Expression<String>)vars[1];
        return true;
    }

    @Override
    protected String[] get(Event e) {
        return new String[] { Main.vt.varData.getObjectStringData(this.objectname.getSingle(e), this.varname.getSingle(e)) };
    }
    @Override
    public void change(Event e, @Nullable Object[] ob, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            assert ob != null;
            Main.vt.varData.setObjectStringData(this.objectname.getSingle(e), this.varname.getSingle(e), ob[0].toString());
        } else if (mode == Changer.ChangeMode.DELETE) {
            Main.vt.varData.delObjectStringData(this.objectname.getSingle(e), this.varname.getSingle(e));
        }
    }
    @Nullable
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if ((mode == Changer.ChangeMode.SET) || (mode == Changer.ChangeMode.DELETE)) {
            return (Class[])CollectionUtils.array(new Class[] { String.class });
        }
        return null;
    }

    @Override
    public Class<? extends String> getReturnType()
    {
        return String.class;
    }

    @Override
    public String toString(Event e, boolean debug)
    {
        return "Jihsk ExprVTvarString";
    }

    @Override
    public boolean isSingle()
    {
        return true;
    }
}