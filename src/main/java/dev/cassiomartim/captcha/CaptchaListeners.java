package dev.cassiomartim.captcha;

import dev.cassiomartim.Main;
import dev.cassiomartim.event.TimeSecondEvent;
import dev.cassiomartim.event.captcha.*;
import dev.cassiomartim.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class CaptchaListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    private void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && event.getInventory().getTitle().equals("Captcha")
                && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
            String display = event.getCurrentItem().getItemMeta().getDisplayName();
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);

            if (display.equals("§aCorreto"))
                Bukkit.getPluginManager().callEvent(new PlayerCaptchaCompleteEvent(player));
            if (display.equals("§cErrado"))
                Bukkit.getPluginManager().callEvent(new PlayerCaptchaWrongEvent(player));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            String title = event.getInventory().getTitle();

            if (title.equals("Captcha")) {
                if (CaptchaApi.hasPlayerInCaptcha(player)) {
                    Util.createCaptchaInventory(player);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        CaptchaApi.addPlayerToCaptcha(player, 120);
        if (CaptchaApi.hasPlayerInCaptcha(player)) {
            Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable() {
                @Override
                public void run() {
                    Util.createCaptchaInventory(player);
                }
            }, 20L);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CaptchaApi.removePlayerFromCaptcha(player);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onSecondEvent(TimeSecondEvent event) {
        CaptchaApi.getPlayersInCaptcha().forEach(player -> {
            long remaingTime = CaptchaApi.getPlayerCaptchaRemainingTime(player);

            if (remaingTime <= 0) {
                player.closeInventory();
                player.kickPlayer(Util.getTimeExpiredMessage());
            } else {
                CaptchaApi.setPlayerCaptchaTime(player, remaingTime - 1);
            }
        });
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onPlayerCaptchaComplete(PlayerCaptchaCompleteEvent event) {
        Player player = event.getPlayer();
        CaptchaApi.removePlayerFromCaptcha(player);

        player.closeInventory();
        player.sendMessage(Util.getCompleteMessage());
        player.playSound(player.getLocation(), Sound.NOTE_PLING, 2F, 2F);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onPlayerCaptchaWrong(PlayerCaptchaWrongEvent event) {
        Player player = event.getPlayer();
        player.closeInventory();
        player.kickPlayer(Util.getWrongMessage());
    }
}