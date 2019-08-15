package me.lilac.voidenchantments.utils;

import org.bukkit.ChatColor;

public enum Rarity {

    LEGENDARY("Legendary", ChatColor.LIGHT_PURPLE),
    EPIC("Epic", ChatColor.YELLOW),
    RARE("Rare", ChatColor.RED),
    UNCOMMON("Uncommon", ChatColor.GREEN),
    COMMON("Common", ChatColor.AQUA);

    private String name;
    private ChatColor color;

    Rarity(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }
}
