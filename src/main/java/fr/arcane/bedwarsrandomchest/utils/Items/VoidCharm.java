package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class VoidCharm {

    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.BEDROCK, 1);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&0VoidCharm"));

        mItem.addEnchant(Enchantment.DURABILITY, 1, true);

        lore.add(ChatColor.translateAlternateColorCodes('&', "&8Te ramène à la surface quand tu tombes dans le vide"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8Sneak pour arréter l'effet"));

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
