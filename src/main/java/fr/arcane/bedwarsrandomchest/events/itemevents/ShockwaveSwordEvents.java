package fr.arcane.bedwarsrandomchest.events.itemevents;

import fr.arcane.bedwarsrandomchest.BedwarsRandomChest;
import fr.arcane.bedwarsrandomchest.utils.Items.ShockwaveSword;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class ShockwaveSwordEvents implements Listener {

    private final HashMap<UUID, Long> cooldown = new HashMap<>();



    @EventHandler
    public void onRightClickWithPoppy(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final int cooltime = 90;


        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getType() == Material.IRON_SWORD && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ShockwaveSword.getName()) || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.IRON_SWORD && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ShockwaveSword.getName())) { //check juste si c'est le bon item

            if (cooldown.containsKey(p.getUniqueId())) {
                long secondsleft = ((cooldown.get(p.getUniqueId()) / 1000) + cooltime) - (System.currentTimeMillis() / 1000);
                if (secondsleft > 0) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &3Shockwave Sword &6est toujours en cooldown pour encore : " + secondsleft + " secondes"));
                } else {
                    cooldown.remove(p.getUniqueId());

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &3Shockwave Sword &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                    cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                    new BukkitRunnable() { // execution du sort si tout est vérifié

                        final Location loc = p.getLocation();
                        final Vector direction = loc.getDirection().normalize();
                        double t = 0;

                        @Override
                        public void run() {
                            // --------------Calcul de la trajectoire -------
                            t = t + 5;
                            double x = direction.getX() * t;
                            double y = direction.getY() * t + 1.5;
                            double z = direction.getZ() * t;
                            loc.add(x, y, z);

                            // --------------Calcul de la trajectoire -------

                            p.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 3, false, true);


                            if (loc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.SNOW && loc.getBlock().getType() != Material.GRASS && loc.getBlock().getType() != Material.LONG_GRASS) {
                                this.cancel(); //pour éviter de stopper le rayon quand le touche de l'herbe ou de la neige par exemple
                            }


                            loc.subtract(x, y, z);

                            if (t > 30) {
                                this.cancel();
                            }

                        }

                    }.runTaskTimer(BedwarsRandomChest.plugin, 0, 5);

                }

            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &3Shockwave Sword &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                new BukkitRunnable() { // execution du sort si tout est vérifié

                    final Location loc = p.getLocation();
                    final Vector direction = loc.getDirection().normalize();
                    double t = 0;

                    @Override
                    public void run() {
                        // --------------Calcul de la trajectoire -------
                        t = t + 5;
                        double x = direction.getX() * t;
                        double y = direction.getY() * t + 1.5;
                        double z = direction.getZ() * t;
                        loc.add(x, y, z);

                        // --------------Calcul de la trajectoire -------

                        p.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 3, false, true);


                        if (loc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.SNOW && loc.getBlock().getType() != Material.GRASS && loc.getBlock().getType() != Material.LONG_GRASS) {
                            this.cancel(); //pour éviter de stopper le rayon quand le touche de l'herbe ou de la neige par exemple
                        }


                        loc.subtract(x, y, z);

                        if (t > 30) {
                            this.cancel();
                        }

                    }

                }.runTaskTimer(BedwarsRandomChest.plugin, 0, 5);
            }
        }
    }
}
