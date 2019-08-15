package me.lilac.voidenchantments.enchantments;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractWeaponEnchantment extends AbstractEnchantment {

    public AbstractWeaponEnchantment(String name) {
        super(name);
    }

    public static void init(AbstractWeaponEnchantment instance) {
        instance.getPlugin().getPluginManager().registerEvents(new Listener() {
           @EventHandler
           public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
               if (!(event.getDamager() instanceof Player)) return;
               Player player = (Player) event.getDamager();
               ItemStack item = player.getInventory().getItemInMainHand();

               if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasEnchant(instance)) return;
               int level = item.getItemMeta().getEnchantLevel(instance);
               instance.onHit(player, event.getEntity(), level);
           }
        }, instance.getPlugin());
    }

    public abstract void onHit(Player player, Entity entity, int level);
}
