package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import javax.annotation.Nullable;

import com.jih10157.Jihsk.Main;
import com.jih10157.Jihsk.util.InGame.SimpleScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffScoreboard extends Effect {
    static {
        Skript.registerEffect(EffScoreboard.class, "[jih[sk].[ ]]create new sidebar title %string% text %strings% to %players%");
        Main.Effamount++;
    }
    private Expression<String> title;
    private Expression<String> text;
    private Expression<Player> players;
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.title = (Expression<String>)arg0[0];
        this.text = (Expression<String>)arg0[1];
        this.players = (Expression<Player>)arg0[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) { return "Jihsk EffScoreboard"; }

    @Override
    protected void execute(Event e) {
        String title = this.title.getSingle(e);
        Player[] player = this.players.getAll(e);
        SimpleScoreboard scoreboard = new SimpleScoreboard(ChatColor.translateAlternateColorCodes('&', title));
        String[] texts = this.text.getAll(e);
        for(String text : texts) {
            scoreboard.add(ChatColor.translateAlternateColorCodes('&', text));
        }
        scoreboard.build();
        scoreboard.send(player);
        }
}