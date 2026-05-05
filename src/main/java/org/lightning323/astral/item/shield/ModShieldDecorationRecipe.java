package org.lightning323.astral.item.shield;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShieldDecorationRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.lightning323.astral.registries.AstralRecipes;

public class ModShieldDecorationRecipe extends ShieldDecorationRecipe {
    // 1.21 use the "c" namespace for common tags or your mod ID
    public static final TagKey<Item> SHIELD_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("c", "shields"));

    public ModShieldDecorationRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        ItemStack shieldStack = ItemStack.EMPTY;
        ItemStack bannerStack = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); ++i) {
            ItemStack itemstack = input.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof BannerItem) {
                    if (!bannerStack.isEmpty()) return false;
                    bannerStack = itemstack;
                } else {
                    // 1.21 Check: Use DataComponents to check for existing patterns instead of NBT
                    if (!itemstack.is(SHIELD_TAG) ||
                            !shieldStack.isEmpty() ||
                            !itemstack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY).layers().isEmpty()) {
                        return false;
                    }
                    shieldStack = itemstack;
                }
            }
        }

        return !shieldStack.isEmpty() && !bannerStack.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack shieldStack = ItemStack.EMPTY;
        ItemStack bannerStack = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); ++i) {
            ItemStack itemstack = input.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof BannerItem) {
                    bannerStack = itemstack;
                } else if (itemstack.is(SHIELD_TAG)) {
                    shieldStack = itemstack.copy();
                }
            }
        }

        if (shieldStack.isEmpty() || bannerStack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        // 1.21 Component Logic:
        // Copy patterns and base color from the banner to the shield
        BannerPatternLayers patterns = bannerStack.get(DataComponents.BANNER_PATTERNS);
        if (patterns != null) {
            shieldStack.set(DataComponents.BANNER_PATTERNS, patterns);
        }

        shieldStack.set(DataComponents.BASE_COLOR, ((BannerItem)bannerStack.getItem()).getColor());

        return shieldStack;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AstralRecipes.SHIELD_DECORATION.get();
    }
}