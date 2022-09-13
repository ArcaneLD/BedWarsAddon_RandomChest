package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InfiniteBlock {

    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.WOOL, 4);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r&kaa&r &bInfinite Block&r &kaa&r"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&bPermet de poser le block séléctionné à l'infini"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9(Sneak-ClickDroit pour choisir un block)"));

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
