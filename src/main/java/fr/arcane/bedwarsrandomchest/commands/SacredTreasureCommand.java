package fr.arcane.bedwarsrandomchest.commands;

import fr.arcane.bedwarsrandomchest.utils.Items.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class SacredTreasureCommand implements CommandExecutor {

    public static String SacredTreasureInvName = ChatColor.translateAlternateColorCodes('&', "&6&lItems Custom du plugin :");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if(cmd.getName().equalsIgnoreCase("sacredtreasure")) {
                Player p = (Player) sender;

                Inventory inv = Bukkit.createInventory(null, 18, SacredTreasureInvName);

                inv.setItem(0, MagicalPoppy.BuildItem());
                inv.setItem(1, HermesSword.BuildItem());
                inv.setItem(2, InfiniteBlock.BuildItem());
                inv.setItem(3, FourLifeObsidian.BuildItem());
                inv.setItem(4, PickOfThieves.BuildItem());
                inv.setItem(5, ShockwaveSword.BuildItem());
                inv.setItem(6, SunAxe.BuildItem());
                inv.setItem(7, BigSilverfishBall.BuildItem());
                inv.setItem(8, VoidCharm.BuildItem());
                inv.setItem(9, SwapBow.BuildItem());


                p.openInventory(inv);

            }
        }
        return false;
    }
}
