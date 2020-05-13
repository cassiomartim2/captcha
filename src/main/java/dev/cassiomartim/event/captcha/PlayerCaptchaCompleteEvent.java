package dev.cassiomartim.event.captcha;

import dev.cassiomartim.event.PlayerCaptchaEvent;
import org.bukkit.entity.Player;

public class PlayerCaptchaCompleteEvent extends PlayerCaptchaEvent {

    public PlayerCaptchaCompleteEvent(Player player) {
        super(player);
    }
}