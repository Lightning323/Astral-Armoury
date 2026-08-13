package org.lightning323.astral.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.lightning323.astral.Astral;

public class AstralEnchantments {

    /*
    * Higher weight = more common when the game randomly selects enchantments.
    Weight	Rough rarity
    10	Common
    5	Uncommon
    2	Rare
    1	Very rare
    * */


    public static final ResourceKey<Enchantment> SOULBOUND =
            ResourceKey.create(
                    Registries.ENCHANTMENT,
                    Astral.resource("soulbound")
            );



}