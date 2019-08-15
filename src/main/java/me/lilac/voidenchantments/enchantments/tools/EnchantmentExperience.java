package me.lilac.voidenchantments.enchantments.tools;

import me.lilac.voidenchantments.enchantments.AbstractWeaponEnchantment;
import me.lilac.voidenchantments.utils.Rarity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantmentExperience extends AbstractWeaponEnchantment {

    public EnchantmentExperience(String name) {
        super(name);
    }

    @Override
    public void onHit(Player player, Entity entity, int level) {
        return;
    }

    @Override
    public void onKill(Player player, Entity entity, int level, EntityDeathEvent event) {
        event.setDroppedExp(event.getDroppedExp()*level+1);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return itemStack.getType().toString().endsWith("AXE")
                || itemStack.getType().toString().endsWith("SWORD");
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }
}
