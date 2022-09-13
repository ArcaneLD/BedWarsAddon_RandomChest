package fr.arcane.bedwarsrandomchest.events.itemevents;


import fr.arcane.bedwarsrandomchest.BedwarsRandomChest;
import fr.arcane.bedwarsrandomchest.utils.Items.MagicalPoppy;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

public class MagicalPoppyEvents implements Listener {

    @EventHandler
    public void onRightClickWithPoppy(PlayerInteractEvent e) {
        final Player p = e.getPlayer();

        // ------------------------------------------- Magical Poppy ------------------------------------------------

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getType() == Material.RED_ROSE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(MagicalPoppy.getName()) || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.RED_ROSE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(MagicalPoppy.getName())) { //check juste si c'est le bon item



                new BukkitRunnable() { // execution du sort si tout est vérifié

                    final Location loc = p.getLocation();
                    final Vector direction = loc.getDirection().normalize();
                    double t = 0;

                    @Override
                    public void run() {
                        // --------------Calcul de la trajectoire -------
                        t = t + 1;
                        double x = direction.getX() * t;
                        double y = direction.getY() * t + 1.5;
                        double z = direction.getZ() * t;
                        loc.add(x, y, z);

                        // --------------Calcul de la trajectoire -------
                        Vector v = loc.getDirection();

                        ParticleEffect.SPELL_WITCH.display(loc, v, 1, 16, null); // affichage des particule avec une lib parce que la particleAPI de la 1.8 est horrible et que j'adore celle de la 1.7

                        // --------------Fais des dégats si ça touche une entitée vivante-------
                        for (Entity e : loc.getChunk().getEntities()) {
                            if (e.getLocation().distance(loc) < 2.5) {
                                if (!e.equals(p)) {
                                    if (e instanceof LivingEntity) {
                                        ((LivingEntity) e).damage(0.90, p);
                                        this.cancel();
                                    }
                                }
                            }
                        }
                        if (loc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.SNOW && loc.getBlock().getType() != Material.GRASS && loc.getBlock().getType() != Material.LONG_GRASS) {
                            this.cancel(); //pour éviter de stopper le rayon quand le touche de l'herbe ou de la neige par exemple
                        }


                        loc.subtract(x, y, z);

                        if (t > 30) {
                            this.cancel();
                        } // cancel le sort au bout de 6 secondes

                    }

                }.runTaskTimer(BedwarsRandomChest.plugin, 0, 1);
            }
        }

    @EventHandler
    public void onPoppyPlace(BlockPlaceEvent e) {

        if (e.getBlock().getType() == Material.RED_ROSE) {
            e.setCancelled(true);
        }

    }
    }


    // ------------------------------------------- Magical Poppy ------------------------------------------------





