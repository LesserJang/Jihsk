package com.jih10157.Jihsk.Expression;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import org.bukkit.block.*;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryHolder;

public class ExprTransferDestinationBlock extends SimpleExpression<Block> {
    static {
        Skript.registerExpression(ExprTransferDestinationBlock.class, Block.class, ExpressionType.SIMPLE, "[jih[sk].[ ]]Destination Block");
        Main.Expramount++;
    }
    @Override
    protected Block[] get(Event event) {
        InventoryHolder inventoryHolder = ((InventoryMoveItemEvent)event).getDestination().getHolder();
        if(inventoryHolder instanceof Chest) {
            Chest b = (Chest)inventoryHolder;
            return new Block[] {b.getBlock()};
        } else if(inventoryHolder instanceof Hopper) {
            Hopper b = (Hopper)inventoryHolder;
            return new Block[] {b.getBlock()};
        } else if(inventoryHolder instanceof Dispenser) {
            Dispenser b = (Dispenser)inventoryHolder;
            return new Block[] {b.getBlock()};
        } else if(inventoryHolder instanceof Dropper) {
            Dropper b = (Dropper)inventoryHolder;
            return new Block[] {b.getBlock()};
        } else if(inventoryHolder instanceof DoubleChest) {
            DoubleChest b = (DoubleChest)inventoryHolder;
            return new Block[] {b.getLocation().getBlock()};
        } else if(inventoryHolder instanceof Furnace) {
            Furnace b = (Furnace)inventoryHolder;
            return new Block[] {b.getBlock()};
        }
        return null;
    }
    @Override
    public Class<? extends Block> getReturnType() { return Block.class; }
    @Override
    public boolean isSingle() { return true; }
    @Override
    public String toString(Event event, boolean b) {return "Jihsk ExprTransferDestinationBlock"; }
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(new Class[] { InventoryMoveItemEvent.class })) {
            Skript.error("Destination Block은 transfer 이벤트에서만 사용 가능합니다.", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
}
