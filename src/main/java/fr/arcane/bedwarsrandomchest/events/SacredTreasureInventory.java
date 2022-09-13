package fr.arcane.bedwarsrandomchest.events;

import fr.arcane.bedwarsrandomchest.commands.SacredTreasureCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SacredTreasureInventory implements Listener {

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getClickedInventory().getName().equalsIgnoreCase(SacredTreasureCommand.SacredTreasureInvName)) {

            e.setCancelled(true);

            ItemStack givedItem = e.getCurrentItem();

            p.getInventory().addItem(givedItem);

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Vous avez reçu un.e : " + givedItem.getItemMeta().getDisplayName()));

        }

    }
}
