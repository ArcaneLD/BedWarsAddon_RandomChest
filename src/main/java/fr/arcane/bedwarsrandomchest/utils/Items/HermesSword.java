package fr.arcane.bedwarsrandomchest.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HermesSword {

    public static ItemStack BuildItem() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta mItem = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        mItem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bHermes Sword"));
        mItem.addEnchant(Enchantment.DAMAGE_ALL, 1, true);

        lore.add(ChatColor.translateAlternateColorCodes('&', "&dTe rend agile quand tu la tiens et te permet de te téléporter à l'endroit visé"));


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
