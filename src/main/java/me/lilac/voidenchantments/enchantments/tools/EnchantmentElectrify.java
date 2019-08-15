package me.lilac.voidenchantments.enchantments.tools;

import me.lilac.voidenchantments.enchantments.AbstractMiscEnchantment;
import me.lilac.voidenchantments.utils.Rarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class EnchantmentElectrify extends AbstractMiscEnchantment  {

    public EnchantmentElectrify(String name) {
        super(name);
        init(this);
    }

    @Override
    public void onDeath(Player player, Map<ItemStack, Integer> armor) {
        return;
    }

    @Override
    public void onSneak(Player player, Map<ItemStack, Integer> armor) {
        return;
    }

    @Override
    public void onFish(Player player, int level, ItemStack item) {
        ItemStack cooked;
        if (item.getType() == Material.COD) cooked = new ItemStack(Material.COOKED_COD);
        if (item.getType() == Material.SALMON) cooked = new ItemStack(Material.COOKED_SALMON);

    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }

    @Override
    public Rarity getRarity() {
        return null;
    }
}
