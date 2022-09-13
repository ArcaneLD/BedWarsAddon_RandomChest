package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class FourLifeObsidian {

    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.OBSIDIAN, 8);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a4 Lifes Obsidian"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&2Doit etre cassée 4 fois pour vraiment se casser"));


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
