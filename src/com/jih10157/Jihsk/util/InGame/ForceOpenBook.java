package com.jih10157.Jihsk.util.InGame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

public class ForceOpenBook {
    private static Class<?> _CRAFTPLAYER_CLASS;
    private static Method _GETHANDLE_METHOD, _A_METHOD;
    private static Object _ITEMSTACK, _MAIN_HAND;

    static {
        String name = Bukkit.getServer().getClass().getName();
        name = name.substring(name.indexOf("craftbukkit.") + "craftbukkit.".length());
        final String _VERSION = name.substring(0, name.indexOf("."));
        try {
            _CRAFTPLAYER_CLASS = Class.forName("org.bukkit.craftbukkit." + _VERSION + ".entity.CraftPlayer");
            Class<?> _ITEMSTACK_CLASS = Class.forName("net.minecraft.server." + _VERSION + ".ItemStack");
            Class<?> _ENUMHAND_ENUM = Class.forName("net.minecraft.server." + _VERSION + ".EnumHand");

            _GETHANDLE_METHOD = _CRAFTPLAYER_CLASS.getMethod("getHandle");
            _A_METHOD = Class.forName("net.minecraft.server." + _VERSION + ".EntityPlayer").getMethod("a",
                    _ITEMSTACK_CLASS, _ENUMHAND_ENUM);

            for (Object obj : _ENUMHAND_ENUM.getEnumConstants()) {
                if (obj.toString().equals("MAIN_HAND")) {
                    _MAIN_HAND = obj;
                    break;
                }
            }

            _ITEMSTACK = _ITEMSTACK_CLASS.getConstructor(Class.forName("net.minecraft.server." + _VERSION + ".Item"))
                    .newInstance(Class.forName("net.minecraft.server." + _VERSION + ".Items").getField("WRITTEN_BOOK")
                            .get(null));
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public static void openBook(Player player, ItemStack book) {
        ItemStack itemInHand = player.getInventory().getItemInHand();
        player.getInventory().setItemInHand(book);
        try {
            Object craftPlayer = _CRAFTPLAYER_CLASS.cast(player);
            Object entityPlayer = _GETHANDLE_METHOD.invoke(craftPlayer);
            _A_METHOD.invoke(entityPlayer, _ITEMSTACK, _MAIN_HAND);
        } catch (Exception ex) { ex.printStackTrace(); }
        player.getInventory().setItemInHand(itemInHand);
    }
}
