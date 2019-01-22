package com.jih10157.Jihsk.CallEvent;

import com.jih10157.Jihsk.Event.EvtSmartMoving;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class SmartMovingEventListener implements PluginMessageListener {
    public void onPluginMessageReceived(String s, Player pl, byte[] b) {
        StringBuilder st = new StringBuilder();
        for (byte b1 : b) {
            st.append(",").append(b1);
        }
        EvtSmartMoving smartMovingEvent = new EvtSmartMoving(pl, st.toString());
        Bukkit.getPluginManager().callEvent(smartMovingEvent);
    }
}
