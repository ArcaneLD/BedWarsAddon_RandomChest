package fr.arcane.bedwarsrandomchest.events.itemevents;

import fr.arcane.bedwarsrandomchest.BedwarsRandomChest;
import fr.arcane.bedwarsrandomchest.utils.Items.VoidCharm;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

public class VoidCharmEvents implements Listener {


    @EventHandler
    public void onFallInVoid(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (p.getLocation().getY() <= 15) {

            if (p.getInventory().contains(VoidCharm.BuildItem())) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Tes &0Void Charm&6 te ramènent a la surface"));
                p.getInventory().remove(VoidCharm.BuildItem());
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 4000, 60));

                new BukkitRunnable() {

                    @Override
                    public void run() {

                        if (p.isSneaking() || p.getLocation().getY() >= 255) {
                            this.cancel();
                            p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] l'effet as été arrété"));
                        } else {
                            Vector v = new Vector(p.getLocation().getDirection().getX(), 1, p.getLocation().getDirection().getZ());
                            p.setVelocity(v);
                            ParticleEffect.SMOKE_NORMAL.display(p.getLocation(), v, 1, 20,null);
                        }


                    }
                }.runTaskTimer(BedwarsRandomChest.plugin, 0, 15);

            }
        }

    }


    @EventHandler
    public void onBedrockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.BEDROCK && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(VoidCharm.getName())) {

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Tu ne peux pas placer un &0Void Charm &6garde le sur toi pour te sauver d'une chute fatale"));
            e.setCancelled(true);

        }
    }
}
