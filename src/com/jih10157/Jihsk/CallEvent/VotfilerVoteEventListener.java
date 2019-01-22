package com.jih10157.Jihsk.CallEvent;

import com.jih10157.Jihsk.Main;
import com.jih10157.Jihsk.Votifier.EvtVoteEvent;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VotfilerVoteEventListener implements Listener {
    @EventHandler
    public void CallEvent(VotifierEvent e) {
        EvtVoteEvent evtVoteEvent = new EvtVoteEvent(e.getVote().getUsername());
        Main.pm.callEvent(evtVoteEvent);
    }
}
