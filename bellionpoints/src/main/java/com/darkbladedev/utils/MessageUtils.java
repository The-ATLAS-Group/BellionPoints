package com.darkbladedev.utils;

import org.bukkit.ChatColor;

public class MessageUtils {

    public final static String prefix = "&3[&cBellion &5Points&3]&a ";
        
        public static String getColoredMessage(String message) {
            return ChatColor.translateAlternateColorCodes('&', prefix + message);
    }
}
