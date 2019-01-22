package com.jih10157.Jihsk.WorldEdit;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

public class EffSchematic extends Effect {
    private Expression<String> path;
    private Expression<Location> loc;

    static {
        Skript.registerEffect(EffSchematic.class, "[jih[sk].[ ]]Paste Schematic Path: %-string%, Loc[ation]: %location%");
        Main.Effamount++;
    }

    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.path = (Expression<String>) arg0[0];
        this.loc = (Expression<Location>) arg0[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "Jihsk EffSchematic";
    }

    @Override
    protected void execute(Event arg0) {
        File file = new File(this.path.getSingle(arg0));
        if (file.exists()) {
            SchematicFormat format = SchematicFormat.getFormat(file);
            CuboidClipboard clipboard = null;
            try {
                clipboard = format.load(file);
            } catch (IOException | DataException e) {
                e.printStackTrace();
            }
            Vector vector = new Vector(this.loc.getSingle(arg0).getBlockX(), this.loc.getSingle(arg0).getBlockY(), this.loc.getSingle(arg0).getBlockZ());
            EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(this.loc.getSingle(arg0).getWorld()), WorldEdit.getInstance().getConfiguration().maxChangeLimit);
            try {
                clipboard.paste(es, vector, false);
            } catch (MaxChangedBlocksException e) {
                e.printStackTrace();
            }
        }
    }
}
