package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BigSilverfishBall {

    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.SNOW_BALL, 3);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Silverfish Army"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&fFait spawn 10 Silverfish la ou elle tombe"));


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
