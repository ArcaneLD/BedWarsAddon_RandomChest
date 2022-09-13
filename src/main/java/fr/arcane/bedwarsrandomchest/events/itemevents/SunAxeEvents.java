package fr.arcane.bedwarsrandomchest.events.itemevents;

import fr.arcane.bedwarsrandomchest.BedwarsRandomChest;
import fr.arcane.bedwarsrandomchest.utils.Items.SunAxe;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.HashMap;
import java.util.UUID;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SunAxeEvents implements Listener {

    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onRightClickWSunAxe(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        int cooltime = 150;

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getType() == Material.GOLD_AXE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(SunAxe.getName()) || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.GOLD_AXE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(SunAxe.getName())) {

            if (cooldown.containsKey(p.getUniqueId())) {
                long secondsleft = ((cooldown.get(p.getUniqueId()) / 1000) + cooltime) - (System.currentTimeMillis() / 1000);
                if (secondsleft > 0) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &eSun Axe &6est toujours en cooldown pour encore : " + secondsleft + " secondes"));
                } else {
                    cooldown.remove(p.getUniqueId());

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &eSun Axe &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                    cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                    new BukkitRunnable() {

                        double phi = 0;
                        final Location loc = p.getLocation();
                        final Location locw = p.getLocation();

                        @Override
                        public void run() {



                            for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 20) {

                                phi += Math.PI / 10;

                                double r = 0.5;
                                double x = r * cos(theta) * sin(phi);
                                double y = r * cos(phi) + 2.5;
                                double z = r * sin(theta) * sin(phi);

                                double rw = 0.6;
                                double xw = rw * cos(theta) * sin(phi);
                                double yw = rw * cos(phi) + 2.5;
                                double zw = rw * sin(theta) * sin(phi);


                                loc.add(x, y - 1.5, z);
                                Vector v = new Vector();
                                ParticleEffect.FLAME.display(loc, v, 0, 2, null);



                                locw.add(xw, yw - 1.5, zw);
                                Vector vw = new Vector();
                                ParticleEffect.FIREWORKS_SPARK.display(locw, vw, 0, 1, null);


                                for (Entity e : loc.getWorld().getEntities()) {
                                    if (e.getLocation().distance(loc) < 10) {
                                        if (!e.equals(p)) {
                                            if (e instanceof LivingEntity) {
                                                ((LivingEntity) e).damage(0.1, p);
                                                e.setFireTicks(60);

                                            }
                                        }
                                    }
                                }


                                loc.subtract(x, y - 1.5, z);
                                locw.subtract(xw, yw - 1.5, zw);

                                if (phi >= 4000 * Math.PI) {

                                    this.cancel();
                                    p.getWorld().createExplosion(loc, 1F, true);

                                }

                            }
                        }

                    }.runTaskTimer(BedwarsRandomChest.plugin, 0, 1);

                }

            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &eSun Axe &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                 new BukkitRunnable() {

                     final Location loc = p.getLocation();
                     final Location locw = p.getLocation();
                     double phi = 0;

                     @Override
                     public void run() {



                         for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 20) {

                             phi = phi + Math.PI / 10;

                             double r = 3;
                             double x = r * cos(theta) * sin(phi);
                             double y = r * cos(phi) + 2.5;
                             double z = r * sin(theta) * sin(phi);

                             double rw = 0.5;
                             double xw = rw * cos(theta) * sin(phi);
                             double yw = rw * cos(phi) + 2.5;
                             double zw = rw * sin(theta) * sin(phi);


                             loc.add(x, y - 1.5, z);
                             Vector v = new Vector();
                             ParticleEffect.FLAME.display(loc, v, 0, 2, null);


                             locw.add(xw, yw - 1.5, zw);
                             Vector vw = new Vector();
                             ParticleEffect.FIREWORKS_SPARK.display(locw, vw, 0, 1, null);


                             for (Entity e : loc.getWorld().getEntities()) {
                                 if (e.getLocation().distance(loc) < 10) {
                                     if (!e.equals(p)) {
                                         if (e instanceof LivingEntity) {
                                             ((LivingEntity) e).damage(0.1, p);
                                             e.setFireTicks(60);

                                         }
                                     }
                                 }
                             }

                             loc.subtract(x, y - 1.5, z);
                             locw.subtract(xw, yw - 1.5, zw);



                             if (phi >= 4000 * Math.PI) {

                                 p.getWorld().createExplosion(loc, 1F, true);

                                 this.cancel();
                             }

                         }
                     }

                }.runTaskTimer(BedwarsRandomChest.plugin, 0, 1);
            }


        }
    }


}
