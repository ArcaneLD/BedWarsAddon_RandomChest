package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SwapBow {

    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2Swap Bow"));
        mItem.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);

        lore.add(ChatColor.translateAlternateColorCodes('&', "&aEchange Ta place avec celle de l'entité que tu touches (demande des flèches)"));


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
