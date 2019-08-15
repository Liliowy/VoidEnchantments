package me.lilac.voidenchantments.enchantments.tools;

import me.lilac.voidenchantments.enchantments.AbstractMiscEnchantment;
import me.lilac.voidenchantments.utils.Rarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
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
    public void onFish(Player player, int level, PlayerFishEvent event) {
        Item caught = (Item) event.getCaught();
        ItemStack item = caught.getItemStack();
        if (item.getType() == Material.COD) item.setType(Material.COOKED_COD);
        if (item.getType() == Material.SALMON) item.setType(Material.COOKED_SALMON);
        event.getCaught().getWorld().strikeLightningEffect(event.getCaught().getLocation());
    }

    @Override
    public int getMaxLevel() {
        return 1;
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
        return itemStack.getType() == Material.FISHING_ROD;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }
}
