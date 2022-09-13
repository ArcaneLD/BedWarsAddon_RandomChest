package fr.arcane.bedwarsrandomchest.events.itemevents;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

public class BigSilverfishBallEvents implements Listener {

    @EventHandler
    public void onBigSilverfishBallHit(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            e.getEntity().getShooter();
            if (e.getEntity().getType() == EntityType.SNOWBALL) {
                Location loc = e.getEntity().getLocation();

                ParticleEffect.SNOWFLAKE.display(loc, new Vector(), 1, 20, null);

                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                e.getEntity().getWorld().spawnEntity(loc, EntityType.SILVERFISH);

            }
        }
    }
}
