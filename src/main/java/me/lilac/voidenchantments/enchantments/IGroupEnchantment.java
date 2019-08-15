package me.lilac.voidenchantments.enchantments;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IGroupEnchantment {

    void onReceiveCheck(Player player, ItemStack item, int level);

    void onTaskCancelled(Player player, ItemStack item, int level);
}
