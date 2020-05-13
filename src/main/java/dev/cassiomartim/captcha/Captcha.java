package dev.cassiomartim.captcha;

import java.util.Collection;

import org.bukkit.entity.Player;

public interface Captcha {

    public Collection<Player> getPlayersInCaptcha();

    public boolean hasPlayerInCaptcha(Player player);

    public long getPlayerCaptchaRemainingTime(Player player);

    public void addPlayerToCaptcha(Player player, long seconds);

    public void setPlayerCaptchaTime(Player player, long seconds);

    public void removePlayerFromCaptcha(Player player);
}
