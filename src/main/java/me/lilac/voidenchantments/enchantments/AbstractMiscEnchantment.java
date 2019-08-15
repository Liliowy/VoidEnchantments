package me.lilac.voidenchantments.enchantments;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMiscEnchantment extends AbstractEnchantment {

    public AbstractMiscEnchantment(String name) {
        super(name);
    }

    public static void init(AbstractMiscEnchantment instance) {
        instance.getPlugin().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerDeath(PlayerDeathEvent event) {
                Player player = event.getEntity();
                Map<ItemStack, Integer> enchantedArmor = new HashMap<>();

                for (ItemStack armor : player.getInventory().getArmorContents()) {
                    if (armor == null || !armor.hasItemMeta() || !armor.getItemMeta().hasEnchant(instance)) continue;
                    int level = armor.getItemMeta().getEnchantLevel(instance);
                    enchantedArmor.put(armor, level);
                }

                instance.onDeath(player, enchantedArmor);
            }
        }, instance.getPlugin());

        instance.getPlugin().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerSneak(PlayerToggleSneakEvent event) {
                Player player = event.getPlayer();
                Map<ItemStack, Integer> enchantedArmor = new HashMap<>();

                for (ItemStack armor : player.getInventory().getArmorContents()) {
                    if (armor == null || !armor.hasItemMeta() || !armor.getItemMeta().hasEnchant(instance)) continue;
                    int level = armor.getItemMeta().getEnchantLevel(instance);
                    enchantedArmor.put(armor, level);
                }

                instance.onSneak(player, enchantedArmor);
            }
        }, instance.getPlugin());

        // double jump thing

        instance.getPlugin().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerFish(PlayerFishEvent event) {
                ItemStack rod = event.getPlayer().getInventory().getItemInMainHand();
                if (rod == null || !rod.hasItemMeta() || !rod.getItemMeta().hasEnchant(instance)) return;
                if (!(event.getCaught() instanceof Item)) return;
                Item caught = (Item) event.getCaught();
                instance.onFish(event.getPlayer(), rod.getItemMeta().getEnchantLevel(instance), caught.getItemStack());
            }
        }, instance.getPlugin());
    }

    public abstract void onDeath(Player player, Map<ItemStack, Integer> armor);

    public abstract void onSneak(Player player, Map<ItemStack, Integer> armor);

    public abstract void onFish(Player player, int level, ItemStack item);
}
