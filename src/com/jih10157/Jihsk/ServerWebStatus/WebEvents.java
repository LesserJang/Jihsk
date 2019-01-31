package com.jih10157.Jihsk.ServerWebStatus;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.jih10157.Jihsk.Main;
import com.jih10157.ServerWebStatus.events.SocketAcceptEvent;
import com.jih10157.ServerWebStatus.events.WebConnectEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class WebEvents extends SimpleExpression<String>{
    static {
        Skript.registerEvent("jih.SocketAccept", SimpleEvent.class, SocketAcceptEvent.class, "[jih[sk].[ ]]Socket Accept");
        EventValues.registerEventValue(SocketAcceptEvent.class, String.class, new Getter<String, SocketAcceptEvent>() {
            @Override
            public String get(SocketAcceptEvent e) {
                return e.getAddress();
            }
        }, 0);
        Main.Evtamount++;
        Skript.registerEvent("jih.WebConnect", SimpleEvent.class, WebConnectEvent.class, "[jih[sk].[ ]]Web Connect");
        EventValues.registerEventValue(WebConnectEvent.class, String.class, new Getter<String, WebConnectEvent>() {
            @Override
            public String get(WebConnectEvent e) {
                return e.getAddress();
            }
        }, 0);
        Main.Evtamount++;
        Skript.registerExpression(WebEvents.class, String.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]Get Request");

    }
    @Nullable
    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return (Class[]) CollectionUtils.array(new Class[] { String.class });
        }
        return null;
    }
    @Override
    public void change(Event e, @Nullable Object[] ob, Changer.ChangeMode mode) {
        if(mode == Changer.ChangeMode.SET) {
            ((WebConnectEvent)e).setGET((String) (ob != null ? ob[0] : ""));
        }
    }
    @Override
    protected String[] get(Event event) {
        return new String[] {((WebConnectEvent)event).getGET()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Jihsk GetRequest";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return ScriptLoader.isCurrentEvent(WebConnectEvent.class);
    }
}
