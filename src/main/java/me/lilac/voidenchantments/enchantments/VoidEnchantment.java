package me.lilac.voidenchantments.enchantments;

import me.lilac.voidenchantments.utils.Methods;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VoidEnchantment {

    // Common Enchantments
    // electrify, frost aspect, blind, aquatic, haste

    // Uncommon Enchantments
    // confusion, disarm, nutrition, harvestry, medic

    // Rare Enchantments
    // ender, swiftness, anti gravity, self destruct, life steal, zeus

    // Epic Enchantments
    // Double jjump, experience, healtj boost, glowing, boost

    private static List<AbstractEnchantment> enchantments = new ArrayList<AbstractEnchantment>() {{

    }};

    public static void register() {
        try {
            Field acceptingNewField = Enchantment.class.getDeclaredField("acceptingNew");
            acceptingNewField.setAccessible(true);
            acceptingNewField.set(null, true);
            acceptingNewField.setAccessible(false);

            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            byKeyField.setAccessible(true);

            Map<NamespacedKey, Enchantment> byKey = (Map<NamespacedKey, Enchantment>) byKeyField.get(null);

            for (AbstractEnchantment enchantment : enchantments)
                if (!byKey.containsValue(enchantment)) Enchantment.registerEnchantment(enchantment);

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            Methods.sendConsoleMessage(Methods.getPrefix() + "&cThere was an error while registering an enchantment.");
        }
    }

    public static AbstractEnchantment fromString(String string) {
        for (AbstractEnchantment enchantment : enchantments) {
            if (enchantment.getKey().getKey().equalsIgnoreCase(string)) return enchantment;
        }

        return null;
    }

    public static List<AbstractEnchantment> getEnchantments() {
        return enchantments;
    }
}
