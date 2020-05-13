package dev.cassiomartim.event.captcha;

import dev.cassiomartim.event.PlayerCaptchaEvent;
import org.bukkit.entity.Player;

public class PlayerCaptchaWrongEvent extends PlayerCaptchaEvent {

    public PlayerCaptchaWrongEvent(Player player) {
        super(player);
    }
}