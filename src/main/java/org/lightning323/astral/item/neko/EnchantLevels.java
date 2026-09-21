package org.lightning323.astral.item.neko;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

/**
 * Ported from Neko's {@code NekomasFixed.enchantLevel}: returns the total level of
 * all enchantments on the stack whose registered name contains the given string.
 * Works for vanilla enchantments (power, punch, loyalty, riptide, multishot, ...)
 * without depending on enchantment registry holders.
 */
public final class EnchantLevels {
    private EnchantLevels() {
    }

    public static int enchantLevel(ItemStack stack, String name) {
        int level = 0;
        ItemEnchantments enchantments = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        for (Holder<Enchantment> holder : stack.getEnchantments().keySet()) {
            if (holder.getRegisteredName().toLowerCase().contains(name.toLowerCase())) {
                level += enchantments.getLevel(holder);
            }
        }
        return level;
    }
}
