package org.lightning323.astral.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static org.lightning323.astral.AstralArmoury.MOD_ID;

public enum AstralToolTiers implements Tier {
    COPPER(200, Tiers.IRON.getSpeed(), 1.0F, 8,
            () -> Ingredient.of(Items.COPPER_INGOT), "needs_copper_tool"),

    AMETHYST(1000, Tiers.IRON.getSpeed(), Tiers.STONE.getAttackDamageBonus(), 26,
            () -> Ingredient.of(Items.AMETHYST_SHARD), "needs_amethyst_tool"),

    EMERALD((int) (Tiers.DIAMOND.getUses() * 0.75f), 12.0F, 0.0F, 15,
            () -> Ingredient.of(Items.EMERALD), "needs_emerald_tool"),

    CACTUS((int) (Tiers.IRON.getUses() * 0.75f), 12.0F, 0.0F, 15,
            () -> Ingredient.of(Items.CACTUS), "needs_cactus_tool"),

    CREATIVE(Integer.MAX_VALUE, 9999f, 9999f, 99,
            () -> Ingredient.of(Items.NETHER_STAR), "needs_diamond_tool");

    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;
    private final TagKey<Block> tag;

    AstralToolTiers(int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient, String tagName) {
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
        // This fixes the BlockTags.create error:
        this.tag = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, tagName));
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return 0;
    } // 1.20.1 uses Tags mostly

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
//    @Override public TagKey<Block> getTag() { return tag; }
}