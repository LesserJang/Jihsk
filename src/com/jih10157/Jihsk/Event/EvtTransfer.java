package com.jih10157.Jihsk.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.jih10157.Jihsk.Main;
import org.bukkit.block.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class EvtTransfer {
    static {
        Skript.registerEvent("jih.Transfer", SimpleEvent.class, InventoryMoveItemEvent.class, "[jih[sk].[ ]]Transfer");
        EventValues.registerEventValue(InventoryMoveItemEvent.class, ItemStack.class, new Getter<ItemStack, InventoryMoveItemEvent>() {
            @Override
            public ItemStack get(InventoryMoveItemEvent e) {
                return e.getItem();
            }
        }, 0);
        EventValues.registerEventValue(InventoryMoveItemEvent.class, Block.class, new Getter<Block, InventoryMoveItemEvent>() {
            @Override
            public Block get(InventoryMoveItemEvent e) {
                InventoryHolder inventoryHolder = e.getSource().getHolder();
                if(inventoryHolder instanceof Chest) {
                    Chest b = (Chest)inventoryHolder;
                    return b.getBlock();
                } else if(inventoryHolder instanceof Hopper) {
                    Hopper b = (Hopper)inventoryHolder;
                    return b.getBlock();
                } else if(inventoryHolder instanceof Dispenser) {
                    Dispenser b = (Dispenser)inventoryHolder;
                    return b.getBlock();
                } else if(inventoryHolder instanceof Dropper) {
                    Dropper b = (Dropper)inventoryHolder;
                    return b.getBlock();
                } else if(inventoryHolder instanceof DoubleChest) {
                    DoubleChest b = (DoubleChest)inventoryHolder;
                    return b.getLocation().getBlock();
                } else if(inventoryHolder instanceof Furnace) {
                    Furnace b = (Furnace)inventoryHolder;
                    return b.getBlock();
                }
                return null;
            }
        }, 0);
        EventValues.registerEventValue(InventoryMoveItemEvent.class, Entity.class, new Getter<Entity, InventoryMoveItemEvent>() {
            @Override
            public Entity get(InventoryMoveItemEvent e) {
                InventoryHolder inventoryHolder = e.getSource().getHolder();
                if(inventoryHolder instanceof Entity) {
                    return(Entity)inventoryHolder ;
                }
                return null;
            }
        }, 0);
        Main.Evtamount++;
    }
}
