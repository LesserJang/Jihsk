package com.jih10157.Jihsk.ServerWebStatus;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.jih10157.Jihsk.Main;
import com.jih10157.ServerWebStatus.events.SocketAcceptEvent;
import com.jih10157.ServerWebStatus.events.WebConnectEvent;

public class WebEvents {
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
                return e.getGET();
            }
        }, 0);
        Main.Evtamount++;
    }
}
