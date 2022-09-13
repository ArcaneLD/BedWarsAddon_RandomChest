package fr.arcane.bedwarsrandomchest;

import fr.arcane.bedwarsrandomchest.commands.RChestCommand;
import fr.arcane.bedwarsrandomchest.commands.SacredTreasureCommand;
import fr.arcane.bedwarsrandomchest.events.SacredTreasureInventory;
import fr.arcane.bedwarsrandomchest.events.itemevents.BigSilverfishBallEvents;
import fr.arcane.bedwarsrandomchest.events.itemevents.FourLifeObsidianEvents;
import fr.arcane.bedwarsrandomchest.events.itemevents.HermesSwordEvents;
import fr.arcane.bedwarsrandomchest.events.itemevents.InifiniteBlockEvents;
import fr.arcane.bedwarsrandomchest.events.itemevents.MagicalPoppyEvents;
import fr.arcane.bedwarsrandomchest.events.itemevents.PickOfThievesEvents;
import fr.arcane.bedwarsrandomchest.events.itemevents.ShockwaveSwordEvents;
import fr.arcane.bedwarsrandomchest.events.itemevents.SunAxeEvents;
import fr.arcane.bedwarsrandomchest.events.itemevents.SwapBowEvents;
import fr.arcane.bedwarsrandomchest.events.itemevents.VoidCharmEvents;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Random;


public final class BedwarsRandomChest extends JavaPlugin {

    public static BedwarsRandomChest plugin;

    @Override
    public void onEnable() {
        loadconfig();
        plugin = this;


        this.getCommand("rchest").setExecutor(new RChestCommand());
        this.getCommand("sacredtreasure").setExecutor(new SacredTreasureCommand());

        getServer().getPluginManager().registerEvents(new MagicalPoppyEvents(), this);
        getServer().getPluginManager().registerEvents(new HermesSwordEvents(), this);
        getServer().getPluginManager().registerEvents(new SunAxeEvents(), this);
        getServer().getPluginManager().registerEvents(new ShockwaveSwordEvents(), this);
        getServer().getPluginManager().registerEvents(new SwapBowEvents(), this);
        getServer().getPluginManager().registerEvents(new FourLifeObsidianEvents(), this);
        getServer().getPluginManager().registerEvents(new BigSilverfishBallEvents(), this);
        getServer().getPluginManager().registerEvents(new VoidCharmEvents(), this);
        getServer().getPluginManager().registerEvents(new InifiniteBlockEvents(), this);
        getServer().getPluginManager().registerEvents(new PickOfThievesEvents(), this);

        getServer().getPluginManager().registerEvents(new SacredTreasureInventory(), this);

        System.out.println("[RandomChest] Started.");

    }
    public void loadconfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static Location getRandomMapConfigLocValue(World world) {
        Location loc;
        Random r = new Random();
        ArrayList<Location> loclist = new ArrayList<>();
        String Map = world.getName();

        for (int count = 0; count <= plugin.getConfig().getInt("count"); count++) {

            double x = plugin.getConfig().getDouble(Map + "." + count + ".loc.x");
            double y = plugin.getConfig().getDouble(Map + "." + count + ".loc.y");
            double z = plugin.getConfig().getDouble(Map + "." + count + ".loc.z"); // recuperation de la loc depuis la config

            loc = new Location(world, x,y,z);
            loclist.add(loc);
        }
        int randItem = r.nextInt(loclist.size());

        return loclist.get(randItem);
    }

    public static void setMapConfigValue(String Map, Location coords, int count) {
        if (count <= 10) {
            plugin.getConfig().set(Map + "." + count + ".loc.world", coords.getWorld().getName());
            plugin.getConfig().set(Map + "." + count + ".loc.x", coords.getX());
            plugin.getConfig().set(Map + "." + count + ".loc.y", coords.getY());
            plugin.getConfig().set(Map + "." + count + ".loc.z", coords.getZ()); // construction de la loc
            plugin.getConfig().set("count", count); //update le compteur
            plugin.saveConfig(); //save
        }
    }

    public static void clearMapConfigValue(String Map) {
        plugin.getConfig().set(Map, null); // met a null la valeur de la map
        plugin.saveConfig(); //save
    }

}
