package me.lilac.voidenchantments.enchantments;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractToolEnchantment extends AbstractEnchantment {

    public AbstractToolEnchantment(String name) {
        super(name);
    }

    public static void init(AbstractToolEnchantment instance) {
        instance.getPlugin().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onHeldItem(PlayerItemHeldEvent event) {
                Player player = event.getPlayer();
                ItemStack oldItem = event.getPlayer().getInventory().getItem(event.getPreviousSlot());
                ItemStack newItem = event.getPlayer().getInventory().getItem(event.getNewSlot());

                if (oldItem != null && oldItem.hasItemMeta() && oldItem.getItemMeta().hasEnchant(instance))
                    instance.onItemUnequip(player, oldItem, oldItem.getItemMeta().getEnchantLevel(instance));

                if (newItem != null && newItem.hasItemMeta() && newItem.getItemMeta().hasEnchant(instance))
                    instance.onItemEquip(player, newItem, newItem.getItemMeta().getEnchantLevel(instance));
            }
        }, instance.getPlugin());

        instance.getPlugin().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onBlockBreak(BlockBreakEvent event) {
                Player player = event.getPlayer();
                Block block = event.getBlock();
                ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
                if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasEnchant(instance)) return;
                instance.onBlockBreak(player, block, item.getItemMeta().getEnchantLevel(instance));
            }
        }, instance.getPlugin());
    }

    public abstract void onItemEquip(Player player, ItemStack item, int level);

    public abstract void onItemUnequip(Player player, ItemStack item, int level);

    public abstract void onBlockBreak(Player player, Block block, int level);
}
