package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShockwaveSword {

    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3Shockwave Sword"));
        mItem.addEnchant(Enchantment.DAMAGE_ALL, 2, true);

        lore.add(ChatColor.translateAlternateColorCodes('&', "&dProvoque une grande vague de dévastation massive"));


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
