package me.lilac.voidenchantments.enchantments;

import me.lilac.voidenchantments.VoidEnchantments;
import me.lilac.voidenchantments.utils.Rarity;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractEnchantment extends Enchantment {

    private VoidEnchantments plugin;
    private String name;

    public AbstractEnchantment(String name) {
        super(new NamespacedKey(VoidEnchantments.getInstance(),
                name.toLowerCase().replace(' ', '_')));
        this.plugin = VoidEnchantments.getInstance();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public abstract int getMaxLevel();

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public abstract EnchantmentTarget getItemTarget();

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public abstract boolean conflictsWith(Enchantment enchantment);

    @Override
    public abstract boolean canEnchantItem(ItemStack itemStack);

    public abstract Rarity getRarity();

    public VoidEnchantments getPlugin() {
        return plugin;
    }
}
