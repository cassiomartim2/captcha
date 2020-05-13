package dev.cassiomartim.utils;

import java.util.Random;

import dev.cassiomartim.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Util {

    public static void createCaptchaInventory(Player player) {
        Inventory inventory = new InventoryUtils("Captcha", 27).getInventory();

        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, ItemCreator.getItem().itemSkullUrl("§cErrado",
                    "http://textures.minecraft.net/texture/3f87fc5cbb233743a82fb0fa51fe739487f29bcc01c9026621ecefad197f4fb1"));
        }

        inventory.setItem(new Random().nextInt(27), ItemCreator.getItem().itemSkullUrl("§aCorreto",
                "http://textures.minecraft.net/texture/8e732af7dd9c13f6b1eb8b71bf373bbe02b8aeb3fcf69b3eb40d6d29b4ff8420"));
        player.openInventory(inventory);
    }

    public static String color(String message) {
        return message.replace("&", "§");
    }

    public static String getTimeExpiredMessage() {
        return color(Main.getPlugin(Main.class).getConfig().getString("timeExpired"));
    }
    public static String getCompleteMessage() {
        return color(Main.getPlugin(Main.class).getConfig().getString("completeCaptcha"));
    }

    public static String getWrongMessage() {
        return color(Main.getPlugin(Main.class).getConfig().getString("wrongCaptcha"));
    }
}