package me.lilac.voidenchantments.tasks;

import me.lilac.voidenchantments.enchantments.AbstractEnchantment;
import me.lilac.voidenchantments.enchantments.IGroupEnchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemCheckTask extends BukkitRunnable {

    private Player player;
    private int slot;
    private AbstractEnchantment enchantment;
    private IGroupEnchantment groupEnchantment;

    public ItemCheckTask(Player player, int slot, AbstractEnchantment enchantment, IGroupEnchantment groupEnchantment) {
        this.player = player;
        this.slot = slot;
        this.enchantment = enchantment;
        this.groupEnchantment = groupEnchantment;
    }

    @Override
    public void run() {
        ItemStack item = player.getInventory().getItem(slot);
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasEnchant(enchantment)) stop();
        groupEnchantment.onReceiveCheck(player, item, item.getItemMeta().getEnchantLevel(enchantment));
    }

    public void stop() {
        groupEnchantment.onTaskCancelled(player, player.getInventory().getItem(slot),
                player.getInventory().getItem(slot).getItemMeta().getEnchantLevel(enchantment));
        this.cancel();
    }
}
