package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SunAxe {

    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.GOLD_AXE, 1);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
        mItem.addEnchant(Enchantment.DURABILITY, 10, true);

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eSun Axe"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Le soleil des Télétubbies droit dans ta gueule"));


        mItem.setLore(lore);

        item.setItemMeta(mItem);

        return item;
    }

    public static String getName() {

        ItemStack item = BuildItem();
        ItemMeta mItem = item.getItemMeta();

        return mItem.getDisplayName();
    }
}
