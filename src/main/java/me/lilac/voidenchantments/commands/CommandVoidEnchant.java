package me.lilac.voidenchantments.commands;

import me.lilac.voidenchantments.VoidEnchantments;
import me.lilac.voidenchantments.enchantments.AbstractEnchantment;
import me.lilac.voidenchantments.enchantments.VoidEnchantment;
import me.lilac.voidenchantments.utils.Methods;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.text.WordUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandVoidEnchant implements CommandExecutor, TabCompleter {

    private VoidEnchantments plugin;

    public CommandVoidEnchant() {
        plugin = VoidEnchantments.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("voidenchant.use")) {
                if (args.length == 0) {
                    player.sendMessage(Methods.format(Methods.getPrefix() + "/venchant <enchantment> [level]"));
                } else if (args.length == 1) {
                    AbstractEnchantment enchantment = VoidEnchantment.fromString(args[0]);
                    ItemStack heldItem = player.getInventory().getItemInMainHand();
                    tryEnchantItem(enchantment, 1, player, heldItem);
                } else if (args.length == 2) {
                    if (isInt(args[1])) {
                        AbstractEnchantment enchantment = VoidEnchantment.fromString(args[0]);
                        ItemStack heldItem = player.getInventory().getItemInMainHand();
                        int level = Integer.parseInt(args[1]);
                        tryEnchantItem(enchantment, level, player, heldItem);
                    } else {
                        player.sendMessage(Methods.format(Methods.getPrefix() + "&cThis is not a number!"));
                    }
                }
            }
        } else {
            Methods.sendConsoleMessage(Methods.getPrefix() + "&cThe console cannot use this command!");
        }

        return false;
    }

    private void tryEnchantItem(AbstractEnchantment enchantment, int level, Player player, ItemStack item) {
        if (enchantment != null) {
            if (player.getInventory().getItemInMainHand() != null
                    && item.hasItemMeta()
                    && item.getItemMeta().hasEnchant(enchantment)) {
                player.sendMessage(Methods.format(Methods.getPrefix() + "&cThis item cannot be enchanted!"));
                return;
            }

            if (level > enchantment.getMaxLevel()) {
                player.sendMessage(Methods.format(Methods.getPrefix() + "&cThe max level for this enchantment is " + enchantment.getMaxLevel() + "!"));
                return;
            }

            if (player.getInventory().getItemInMainHand() != null && enchantment.canEnchantItem(item)) {
                item.addEnchantment(enchantment, level);
                List<String> lore = new ArrayList<>();
                String numeral = level == 1 ? "" : Methods.getRomanNumeral(level);
                lore.add(Methods.format("&7" + enchantment.getName() + " " + numeral));

                if (item.hasItemMeta() && item.getItemMeta().hasLore())
                    for (String line : item.getItemMeta().getLore()) lore.add(line);

                String name = Methods.format(enchantment.getRarity().getColor() +
                        WordUtils.capitalize(item.getType().toString().toLowerCase().replace('_', ' ')));
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(name);
                meta.setLore(lore);
                item.setItemMeta(meta);

                player.sendMessage(Methods.format(Methods.getPrefix() + "Your item has been enchanted."));
                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
            } else {
                player.sendMessage(Methods.format(Methods.getPrefix() + "&cThis item cannot be enchanted!"));
                return;
            }
        } else {
            player.sendMessage(Methods.format(Methods.getPrefix() + "&cThis enchantment does not exist!"));
            return;
        }
    }

    private boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String string, String[] args) {
        if(!(sender instanceof Player)) return null;
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        List<String> tab = new ArrayList<>();

        if (args.length == 1) {
            for (AbstractEnchantment enchantment : VoidEnchantment.getEnchantments()) {
                if (item.hasItemMeta() && item.getItemMeta().hasEnchant(enchantment)) continue;
                if (args[0].isEmpty()) {
                    if (enchantment.canEnchantItem(item)) tab.add(enchantment.getKey().getKey());
                } else {
                    if (enchantment.canEnchantItem(item)
                            && enchantment.getKey().getKey().contains(args[0]))
                        tab.add(enchantment.getKey().getKey());
                }
            }
        } else if (args.length == 2) {
            if (VoidEnchantment.fromString(args[0]) == null) return tab;
            for (int i = 1; i < VoidEnchantment.fromString(args[0]).getMaxLevel()+1; i++)
                tab.add(String.valueOf(i));
        }

        return tab.isEmpty() ? Arrays.asList("You cannot enchant this item!") : tab;
    }
}
