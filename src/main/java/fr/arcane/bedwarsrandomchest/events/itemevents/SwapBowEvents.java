package fr.arcane.bedwarsrandomchest.events.itemevents;

import fr.arcane.bedwarsrandomchest.utils.Items.SwapBow;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.HashMap;
import java.util.UUID;

public class SwapBowEvents implements Listener {

    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onHitWSwapBow(EntityDamageByEntityEvent e) {
        final int cooltime = 15;
        if (e.getDamager() instanceof Arrow) {
            Arrow a = (Arrow) e.getDamager();
            if (a.getShooter() instanceof Player) {
                Player p = (Player) a.getShooter();

                if (p.getItemInHand().getType() == Material.BOW && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(SwapBow.getName())) {

                    if (cooldown.containsKey(p.getUniqueId())) {
                        long secondsleft = ((cooldown.get(p.getUniqueId()) / 1000) + cooltime) - (System.currentTimeMillis() / 1000);
                        if (secondsleft > 0) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &2Swap Bow &6est toujours en cooldown pour encore : " + secondsleft + " secondes"));
                        } else {
                            cooldown.remove(p.getUniqueId());

                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &2Swap Bow &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                            cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                            Location shooted = e.getEntity().getLocation();
                            Location shooter = p.getLocation();

                            e.getEntity().teleport(shooter);
                            p.teleport(shooted);
                            Vector v = new Vector();
                            ParticleEffect.FIREWORKS_SPARK.display(shooted, v, 1, 8, null);
                            ParticleEffect.FIREWORKS_SPARK.display(shooter, v, 1, 8, null);

                        }

                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &2Swap Bow &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                        cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                        Location shooted = e.getEntity().getLocation();
                        Location shooter = p.getLocation();

                        e.getEntity().teleport(shooter);
                        p.teleport(shooted);
                        Vector v = new Vector();
                        ParticleEffect.FIREWORKS_SPARK.display(shooted, v, 1, 8, null);
                        ParticleEffect.FIREWORKS_SPARK.display(shooter, v, 1, 8, null);
                    }
                }
            }
        }
    }
}
