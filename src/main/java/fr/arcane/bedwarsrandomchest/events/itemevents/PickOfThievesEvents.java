package fr.arcane.bedwarsrandomchest.events.itemevents;

import fr.arcane.bedwarsrandomchest.utils.Items.PickOfThieves;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.HashMap;
import java.util.UUID;

public class PickOfThievesEvents implements Listener {

    private HashMap<UUID, Long> cooldown = new HashMap<>();


    @EventHandler
    public void OnRightClickWPickOfThieves(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        int cooltime = 300;

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getType() == Material.GOLD_PICKAXE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(PickOfThieves.getName()) || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.GOLD_PICKAXE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(PickOfThieves.getName())) {

            if (cooldown.containsKey(p.getUniqueId())) {
                long secondsleft = ((cooldown.get(p.getUniqueId()) / 1000) + cooltime) - (System.currentTimeMillis() / 1000);
                if (secondsleft > 0) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &ePick Of Thieves &6est toujours en cooldown pour encore : " + secondsleft + " secondes"));
                } else {
                    cooldown.remove(p.getUniqueId());

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &ePick Of Thieves &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                    cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                    ParticleEffect.DRAGON_BREATH.display(p.getLocation(), p.getLocation().getDirection(), 0, 80, null);

                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 1));

                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000, 2));

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Vous etes maintenant aussi discret et efficace qu'un pilleur pour encore 30 secondes"));
                }

            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &ePick Of Thieves &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                ParticleEffect.DRAGON_BREATH.display(p.getLocation(), p.getLocation().getDirection(), 0, 80, null);

                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 1));

                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000, 2));

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Vous etes maintenant aussi discret et efficace qu'un pilleur pour encore 30 secondes"));
            }


        }
    }

}
