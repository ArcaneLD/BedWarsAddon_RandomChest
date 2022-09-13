package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PickOfThieves {

    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.GOLD_PICKAXE, 1);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Pick of Thieves"));

        mItem.addEnchant(Enchantment.DIG_SPEED, 6, true);
        mItem.addEnchant(Enchantment.DURABILITY, 5, true);

        lore.add(ChatColor.translateAlternateColorCodes('&', "&eParfait Pour casser des beds ou fuir une situation difficile"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&eClick Droit Pour s'octroyer les effets : Speed 3, Invisibilité 2 et Haste 3"));

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
