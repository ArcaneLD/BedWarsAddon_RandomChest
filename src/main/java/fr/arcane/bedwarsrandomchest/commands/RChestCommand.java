package fr.arcane.bedwarsrandomchest.commands;

import fr.arcane.bedwarsrandomchest.BedwarsRandomChest;
import fr.arcane.bedwarsrandomchest.utils.RChestCustomitems;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


public class RChestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("rchest")) {
            if (sender instanceof Player) {
                Player s = (Player)sender;

                if (!args[0].isEmpty()){
                    if (args[0].equalsIgnoreCase("add")) {
                        Location location = s.getLocation();
                        World world = s.getWorld();
                        int count = BedwarsRandomChest.plugin.getConfig().getInt("count") + 1;
                        BedwarsRandomChest.setMapConfigValue(world.getName(), location, count);
                        s.sendMessage("§6[RandomChest] un coffre peut désormais être placé à : X - " + location.getX() + ", Y - " + location.getY() + ", Z - " + location.getZ() + " Dans la map : " + world.getName());
                    }
                    if (args[0].equalsIgnoreCase("clear")) {

                        World world = s.getWorld();
                        BedwarsRandomChest.clearMapConfigValue(world.getName());
                        s.sendMessage("§6[RandomChest] Emplacements reset pour la map " + world.getName());
                    }
                    if (args[0].equalsIgnoreCase("start")) {
                        Bukkit.broadcastMessage("§6[RandomChest] Une crate spawnera maintenant toutes les 5 min pendant 1 H");

                            try {

                                new BukkitRunnable() {
                                    int limit = 0;
                                    @Override
                                    public void run() {

                                        if (limit >= 20) {
                                            this.cancel();

                                        } else {
                                            limit++;
                                            Bukkit.broadcastMessage("§6[RandomChest] Une crate est apparue quelque part sur la map !");

                                            Location chestSpawnLoc = BedwarsRandomChest.getRandomMapConfigLocValue(s.getWorld());
                                            ItemStack customItem = RChestCustomitems.getRandomCustomItem();

                                            Block b = chestSpawnLoc.getBlock();
                                            b.setType(Material.TRAPPED_CHEST);
                                            Block belowblock = chestSpawnLoc.subtract(0,1,0).getBlock();
                                            belowblock.setType(Material.GOLD_BLOCK);
                                            Chest chest = (Chest) b.getState();
                                            Inventory inv = chest.getInventory();

                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    b.breakNaturally();
                                                    belowblock.breakNaturally();
                                                }
                                            }.runTaskLater(BedwarsRandomChest.plugin, 1200);


                                            inv.setItem(13, customItem);

                                        }

                                    }
                                }.runTaskTimer(BedwarsRandomChest.plugin, 10L, 2000L);
                            } catch (Exception e) {
                                s.sendMessage("§4[RandomChest] ERROR : " + e);
                            }

                    }
                } else {
                    s.sendMessage("&4[RandomChest] /rchest command usage : /rchest <add|clear|start>");
                }

            }
        }


        return false;
    }
}
