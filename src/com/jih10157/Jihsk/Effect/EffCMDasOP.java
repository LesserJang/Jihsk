package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffCMDasOP extends Effect {
    private Expression<Player> player;
    private Expression<String> command;
    static {
        Skript.registerEffect(EffCMDasOP.class, "[jih[sk].[ ]]run %player% (command|cmd) %string% as op");
        Main.Effamount++;
    }
    @Override
    protected void execute(Event event) {
        for (String command : this.command.getArray(event)) {
            if (command.startsWith("/")) command = command.substring(1);
            if (this.player == null) Skript.dispatchCommand(Bukkit.getConsoleSender(), command);
            else
                for (CommandSender sender : this.player.getArray(event))
                    if (!sender.isOp()) {
                        sender.setOp(true);
                        Skript.dispatchCommand(sender, command);
                        sender.setOp(false);
                    } else {
                        Skript.dispatchCommand(sender, command);
                    }
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Jihsk EffCMDasOP";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        command = (Expression<String>) expressions[1];
        return true;
    }
    //run %player% (command|cmd) %string% as op
}
