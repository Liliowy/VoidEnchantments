package me.lilac.voidenchantments;

import me.lilac.voidenchantments.commands.CommandVoidEnchant;
import me.lilac.voidenchantments.enchantments.VoidEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class VoidEnchantments extends JavaPlugin {

    private static VoidEnchantments instance;
    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        instance = this;
        pluginManager = Bukkit.getPluginManager();

        VoidEnchantment.register();

        getCommand("voidenchant").setExecutor(new CommandVoidEnchant());
    }

    public static VoidEnchantments getInstance() {
        return instance;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }
}
