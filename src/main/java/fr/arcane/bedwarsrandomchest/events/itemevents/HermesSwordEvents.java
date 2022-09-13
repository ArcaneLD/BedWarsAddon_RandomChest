package fr.arcane.bedwarsrandomchest.events.itemevents;

import fr.arcane.bedwarsrandomchest.BedwarsRandomChest;
import fr.arcane.bedwarsrandomchest.utils.Items.HermesSword;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class HermesSwordEvents implements Listener {

    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onRightClickWithHSword(PlayerInteractEvent e) {
        int cooltime = 20;
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getType() == Material.IRON_SWORD && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(HermesSword.getName()) || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.IRON_SWORD && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(HermesSword.getName())) {



            if (cooldown.containsKey(p.getUniqueId())) {
                    long secondsleft = ((cooldown.get(p.getUniqueId()) / 1000) + cooltime) - (System.currentTimeMillis() / 1000);
                    if (secondsleft > 0) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &bHermes Sword &6est toujours en cooldown pour encore : " + secondsleft + " secondes"));
                    } else {
                        cooldown.remove(p.getUniqueId());

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &bHermes Sword &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                        cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                        Block b = p.getTargetBlock((Set<Material>) null, 50);

                        Location tploc = b.getLocation();
                        tploc.add(0,1,0);
                        tploc.setPitch(p.getLocation().getPitch());
                        tploc.setYaw(p.getLocation().getYaw());
                        Vector v = p.getLocation().getDirection().multiply(2);
                        ParticleEffect.SMOKE_LARGE.display(p.getLocation(), v, 1, 16, null);
                        p.teleport(tploc);
                        ParticleEffect.SMOKE_LARGE.display(tploc, v, 1, 16, null);
                    }

                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[BedwarsRandomChest] Ta &bHermes Sword &6est maintenant en cooldown pour : " + cooltime + " secondes"));
                    cooldown.put(p.getUniqueId(), System.currentTimeMillis());

                    Block b = p.getTargetBlock((Set<Material>) null, 30);
                    Location tploc = b.getLocation();
                    tploc.add(0,1,0);
                    tploc.setPitch(p.getLocation().getPitch());
                    tploc.setYaw(p.getLocation().getYaw());
                    Vector v = p.getLocation().getDirection().multiply(2);
                    ParticleEffect.SMOKE_LARGE.display(p.getLocation(), v, 2, 20, null);
                    p.teleport(tploc);
                    ParticleEffect.SMOKE_LARGE.display(tploc, v, 2, 20, null);
                }
            }
            }


    @EventHandler
    public void onChangeHeldItem(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        ItemStack i = e.getPlayer().getInventory().getItem(e.getNewSlot());
        if (!(i == null)) {



            if (i.getType() == Material.IRON_SWORD && i.getItemMeta().getDisplayName().equalsIgnoreCase(HermesSword.getName())) {

                p.addPotionEffect(PotionEffectType.SPEED.createEffect(100, 2), true);
                p.addPotionEffect(PotionEffectType.JUMP.createEffect(100, 1), true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b &lQue la grace de Hermes soit tienne " + p.getName() + " !!"));


                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (p.getItemInHand().getType() == Material.IRON_SWORD && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(HermesSword.getName())) {

                            p.addPotionEffect(PotionEffectType.SPEED.createEffect(100, 2), true);
                            p.addPotionEffect(PotionEffectType.JUMP.createEffect(100, 1), true);

                        } else {
                            this.cancel();
                        }
                    }

                }.runTaskTimer(BedwarsRandomChest.plugin, 20, 20);


            }
        }

    }

}
