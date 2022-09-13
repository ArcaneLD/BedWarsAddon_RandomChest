package fr.arcane.bedwarsrandomchest.events.itemevents;

import fr.arcane.bedwarsrandomchest.utils.Items.FourLifeObsidian;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;

public class FourLifeObsidianEvents implements Listener {

    HashMap<Location, Integer> fourlife = new HashMap<>();

    @EventHandler
    public void Place4LifeObsi(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.OBSIDIAN && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(FourLifeObsidian.getName())) {
            fourlife.put(e.getBlock().getLocation(), 4);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Tu as placé de la &aFour Life Obsidian"));

        }
    }

    @EventHandler
    public void Breack4LifeObsi(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (e.getBlock().getType() == Material.OBSIDIAN && fourlife.containsKey(e.getBlock().getLocation())) {
            if (fourlife.get(e.getBlock().getLocation()) <= 0) {
                fourlife.remove(e.getBlock().getLocation());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Tu as cassé de la &aFour Life Obsidian"));
            } else {
                int newvalue = fourlife.get(e.getBlock().getLocation()) - 1;
                fourlife.put(e.getBlock().getLocation(),newvalue);
                if (newvalue == 0) {
                    fourlife.remove(e.getBlock().getLocation());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Tu as cassé de la &aFour Life Obsidian"));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Tu dois encore le casser &a" + newvalue + "&6 fois"));
                    e.setCancelled(true);
                }
            }


        }
    }


}
