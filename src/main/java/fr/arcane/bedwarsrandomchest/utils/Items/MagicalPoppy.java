package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MagicalPoppy {


    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.RED_ROSE, 1);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.addEnchant(Enchantment.DAMAGE_ALL, 4, true);

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Magical Poppy"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&dTire des particules ensorcelées qui font des dégats à leur cible"));


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
