package fr.arcane.bedwarsrandomchest.utils;

import fr.arcane.bedwarsrandomchest.utils.Items.*;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RChestCustomitems {

    public static ItemStack getRandomCustomItem() {
        Random random = new Random();
        ItemStack returnedItem = null;

        int randint = random.nextInt(10);

        if (randint == 0) {
            returnedItem = FourLifeObsidian.BuildItem();
        }
        if (randint == 1) {
            returnedItem = BigSilverfishBall.BuildItem();
        }
        if (randint == 2) {
            returnedItem = HermesSword.BuildItem();
        }
        if (randint == 3) {
            returnedItem = InfiniteBlock.BuildItem();
        }
        if (randint == 4) {
            returnedItem = MagicalPoppy.BuildItem();
        }
        if (randint == 5) {
            returnedItem = PickOfThieves.BuildItem();
        }
        if (randint == 6) {
            returnedItem = ShockwaveSword.BuildItem();
        }
        if (randint == 7) {
            returnedItem = SunAxe.BuildItem();
        }
        if (randint == 8) {
            returnedItem = SwapBow.BuildItem();
        }
        if (randint == 9) {
            returnedItem = VoidCharm.BuildItem();
        }

        return returnedItem;
    }

}
