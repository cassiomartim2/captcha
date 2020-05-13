package dev.cassiomartim.captcha;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

public final class CaptchaApi {

    private static final Captcha captcha = new Captcha() {

        private final Map<Player, Long> captchaMap = new ConcurrentHashMap<>();

        @Override
        public Collection<Player> getPlayersInCaptcha() {
            return this.captchaMap.keySet();
        }

        @Override
        public boolean hasPlayerInCaptcha(Player player) {
            return this.captchaMap.containsKey(player);
        }

        @Override
        public long getPlayerCaptchaRemainingTime(Player player) {
            return this.captchaMap.getOrDefault(player, 0L);
        }

        @Override
        public void addPlayerToCaptcha(Player player, long seconds) {
            this.captchaMap.putIfAbsent(player, seconds);
        }

        public void setPlayerCaptchaTime(Player player, long seconds) {
            this.captchaMap.put(player, seconds);
        }

        @Override
        public void removePlayerFromCaptcha(Player player) {
            this.captchaMap.remove(player);
        }
    };

    public static Collection<Player> getPlayersInCaptcha() {
        return captcha.getPlayersInCaptcha();
    }

    public static boolean hasPlayerInCaptcha(Player player) {
        return captcha.hasPlayerInCaptcha(player);
    }

    public static long getPlayerCaptchaRemainingTime(Player player) {
        return captcha.getPlayerCaptchaRemainingTime(player);
    }

    public static void addPlayerToCaptcha(Player player, long seconds) {
        captcha.addPlayerToCaptcha(player, seconds);
    }

    public static void setPlayerCaptchaTime(Player player, long seconds) {
        captcha.setPlayerCaptchaTime(player, seconds);
    }

    public static void removePlayerFromCaptcha(Player player) {
        captcha.removePlayerFromCaptcha(player);
    }
}