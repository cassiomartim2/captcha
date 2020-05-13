package dev.cassiomartim;

import dev.cassiomartim.captcha.CaptchaListeners;
import dev.cassiomartim.event.TimeSecondEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private final PluginManager pm = Bukkit.getPluginManager();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.registerListeners();
        super.onEnable();

        Bukkit.getScheduler().runTaskTimer(this, () -> pm.callEvent(new TimeSecondEvent()), 20L, 20L);
        Bukkit.getConsoleSender().sendMessage("§aCaptcha-Plugin inicializado com sucesso.");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Bukkit.getConsoleSender().sendMessage("§cCaptcha-Plugin encerrado com sucesso.");
    }

    public synchronized void registerListeners() {
        pm.registerEvents(new CaptchaListeners(), this);
    }
}
