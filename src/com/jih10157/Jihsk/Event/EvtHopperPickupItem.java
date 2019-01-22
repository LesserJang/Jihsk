package com.jih10157.Jihsk.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.jih10157.Jihsk.Main;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class EvtHopperPickupItem {
    static {
        Skript.registerEvent("jih.HopperPickup", SimpleEvent.class, InventoryPickupItemEvent.class, "[jih[sk].[ ]]Hopper Pickup");
        EventValues.registerEventValue(InventoryPickupItemEvent.class, Block.class, new Getter<Block, InventoryPickupItemEvent>() {
            @Override
            public Block get(InventoryPickupItemEvent inventoryPickupItemEvent) {
                InventoryHolder inventoryHolder = inventoryPickupItemEvent.getInventory().getHolder();
                if(inventoryHolder instanceof Hopper) {
                    return ((Hopper)inventoryHolder).getBlock();
                }
                return null;
            }
        }, 0);
        EventValues.registerEventValue(InventoryPickupItemEvent.class, Entity.class, new Getter<Entity, InventoryPickupItemEvent>() {
            @Override
            public Entity get(InventoryPickupItemEvent inventoryPickupItemEvent) {
                InventoryHolder inventoryHolder = inventoryPickupItemEvent.getInventory().getHolder();
                if(inventoryHolder instanceof HopperMinecart) {
                    return (Entity)inventoryHolder;
                }
                return null;
            }
        }, 0);
        EventValues.registerEventValue(InventoryPickupItemEvent.class, ItemStack.class, new Getter<ItemStack, InventoryPickupItemEvent>() {
            @Override
            public ItemStack get(InventoryPickupItemEvent inventoryPickupItemEvent) {
                return inventoryPickupItemEvent.getItem().getItemStack();
            }
        }, 0);
        Main.Evtamount++;
    }
}
