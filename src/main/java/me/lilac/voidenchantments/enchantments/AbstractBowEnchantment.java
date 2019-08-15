package me.lilac.voidenchantments.enchantments;

import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public abstract class AbstractBowEnchantment extends AbstractEnchantment {

    public AbstractBowEnchantment(String name) {
        super(name);
    }

    public static void init(AbstractBowEnchantment instance) {
        instance.getPlugin().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onShootBow(EntityShootBowEvent event) {
                if (!(event.getEntity() instanceof Player)) return;
                if (!event.getBow().hasItemMeta() || !event.getBow().getItemMeta().hasEnchant(instance)) return;
                if (!(event.getProjectile() instanceof Arrow)) return;
                Arrow arrow = (Arrow) event.getProjectile();
                Player player = (Player) event.getEntity();
                ItemStack bow = event.getBow();
                int level = bow.getItemMeta().getEnchantLevel(instance);
                arrow.setMetadata(instance.getKey().getKey(),
                        new FixedMetadataValue(instance.getPlugin(), level));
                instance.onShoot(player, bow, level);
            }
        }, instance.getPlugin());

        instance.getPlugin().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onProjectileHit(ProjectileHitEvent event) {
                if (!(event.getEntity() instanceof Arrow)) return;
                Arrow arrow = (Arrow) event.getEntity();
                if (arrow.getMetadata(instance.getKey().getKey()) == null) return;
                int level = arrow.getMetadata(instance.getKey().getKey()).get(0).asInt();
                if (event.getHitBlock() != null) instance.onProjectileHitBlock(event.getHitBlock(), arrow, level);
                if (event.getHitEntity() != null) instance.onProjectileHitEntity(event.getHitEntity(), arrow, level);
            }
        }, instance.getPlugin());
    }

    public abstract void onShoot(Player player, ItemStack bow, int level);

    public abstract void onProjectileHitEntity(Entity entity, Arrow arrow, int level);

    public abstract void onProjectileHitBlock(Block block, Arrow arrow, int level);
}
