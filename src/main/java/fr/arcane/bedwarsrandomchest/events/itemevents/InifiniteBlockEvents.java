package fr.arcane.bedwarsrandomchest.events.itemevents;

import fr.arcane.bedwarsrandomchest.utils.Items.InfiniteBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class InifiniteBlockEvents implements Listener {

    String invname = ChatColor.translateAlternateColorCodes('&', "&9&lChoisis un Block :");
    HashMap<UUID, String> mode = new HashMap<>();

    @EventHandler
    public void onInfiniteBlockSelect(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(InfiniteBlock.getName())) {

            if (p.isSneaking()) {

                Inventory inv = Bukkit.createInventory(null, 9, invname);

                inv.setItem(0, new ItemStack(Material.WOOL, 1));
                inv.setItem(1, new ItemStack(Material.HARD_CLAY, 1));
                inv.setItem(2, new ItemStack(Material.GLASS, 1));
                inv.setItem(3, new ItemStack(Material.ENDER_STONE, 1));
                inv.setItem(4, new ItemStack(Material.WOOD, 1));
                inv.setItem(5, new ItemStack(Material.OBSIDIAN, 1));

                p.openInventory(inv);
            }
        }
    }


    @EventHandler
    public void OnInfiniteBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();


        if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(InfiniteBlock.getName())) {

            if (!(mode.containsKey(p.getUniqueId()))) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Séléctionne un block avant de le posé (Sneak-ClickDroit)"));
            } else {
                String blocktype = mode.get(p.getUniqueId());
                ItemStack giveditem = p.getItemInHand();
                giveditem.setAmount(1);
                p.getInventory().addItem(giveditem);

            }
        }

    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getClickedInventory().getName().equalsIgnoreCase(invname)) {
            e.setCancelled(true);
            ItemStack i = e.getCurrentItem();

            if (i.getType() == Material.WOOL) {
                mode.put(p.getUniqueId(), "Wool");
                p.getItemInHand().setType(Material.WOOL);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] You selected Wool"));
            }
            if (i.getType() == Material.HARD_CLAY) {
                mode.put(p.getUniqueId(), "Clay");
                p.getItemInHand().setType(Material.HARD_CLAY);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] You selected Hardened Clay"));
            }
            if (i.getType() == Material.GLASS) {
                mode.put(p.getUniqueId(), "Glass");
                p.getItemInHand().setType(Material.GLASS);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] You selected Glass"));
            }
            if (i.getType() == Material.ENDER_STONE) {
                mode.put(p.getUniqueId(), "Endstone");
                p.getItemInHand().setType(Material.ENDER_STONE);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] You selected End Stone"));
            }
            if (i.getType() == Material.WOOD) {
                mode.put(p.getUniqueId(), "Wood");
                p.getItemInHand().setType(Material.WOOD);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] You selected Wood"));
            }
            if (i.getType() == Material.OBSIDIAN) {
                mode.put(p.getUniqueId(), "Obsidian");
                p.getItemInHand().setType(Material.OBSIDIAN);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] You selected Obsidian"));
            }

        }
    }

}
