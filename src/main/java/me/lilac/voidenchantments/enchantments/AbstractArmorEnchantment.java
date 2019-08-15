package me.lilac.voidenchantments.enchantments;

import me.lilac.voidenchantments.api.ArmorEquipEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractArmorEnchantment extends AbstractEnchantment {

    public AbstractArmorEnchantment(String name, boolean groupEnchantment) {
        super(name);
    }

    public static void init(AbstractArmorEnchantment instance) {
        instance.getPlugin().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onArmorEquip(ArmorEquipEvent event) {
                Player player = event.getPlayer();
                ItemStack oldArmor = event.getOldArmorPiece();
                ItemStack newArmor = event.getNewArmorPiece();

                if (oldArmor != null && oldArmor.hasItemMeta() && oldArmor.getItemMeta().hasEnchant(instance))
                    instance.onArmorUnequip(player, oldArmor, oldArmor.getItemMeta().getEnchantLevel(instance));

                if (newArmor != null && newArmor.hasItemMeta() && newArmor.getItemMeta().hasEnchant(instance))
                    instance.onArmorEquip(player, newArmor, newArmor.getItemMeta().getEnchantLevel(instance));
            }
        }, instance.getPlugin());
    }

    public abstract void onArmorEquip(Player player, ItemStack armor, int level);

    public abstract void onArmorUnequip(Player player, ItemStack armor, int level);
}
