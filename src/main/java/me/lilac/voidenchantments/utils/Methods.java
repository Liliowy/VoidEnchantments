package me.lilac.voidenchantments.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Methods {

    public static String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void sendConsoleMessage(String string) {
        Bukkit.getConsoleSender().sendMessage(format(string));
    }

    public static String getRomanNumeral(int num) {
        String numeral = "";
        switch (num) {
            case 2:
                numeral = "II";
                break;
            case 3:
                numeral = "III";
                break;
        }

        // No higher than this is used within the plugin.

        return numeral;
    }

    public static String getPrefix() {
        return format("&8[&aEnchant&8] &e");
    }
}
