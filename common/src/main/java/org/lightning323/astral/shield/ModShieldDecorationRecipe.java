package org.lightning323.astral.shield;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShieldDecorationRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.lightning323.astral.registries.AstralRecipes;

//See package net.minecraft.world.item.crafting.ShieldDecorationRecipe
public class ModShieldDecorationRecipe extends ShieldDecorationRecipe {
    // Define a tag so other mods can also use your recipe logic if desired
    public static final TagKey<Item> SHIELD_TAG = TagKey.create(Registries.ITEM, 
        new ResourceLocation("forge", "shields")); // Or your mod id

    public ModShieldDecorationRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack shieldStack = ItemStack.EMPTY;
        ItemStack bannerStack = ItemStack.EMPTY;

        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof BannerItem) {
                if (!bannerStack.isEmpty()) return false;
                bannerStack = stack;
            } else {
                // CHANGE: Check for your tag instead of Items.SHIELD
                if (!stack.is(SHIELD_TAG) || !shieldStack.isEmpty() || BlockItem.getBlockEntityData(stack) != null) {
                    return false;
                }
                shieldStack = stack;
            }
        }
        return !shieldStack.isEmpty() && !bannerStack.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        ItemStack banner = ItemStack.EMPTY;
        ItemStack shield = ItemStack.EMPTY;

        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof BannerItem) {
                banner = stack;
            } else if (stack.is(SHIELD_TAG)) {
                shield = stack.copy();
            }
        }

        if (shield.isEmpty()) return ItemStack.EMPTY;

        CompoundTag bannerTag = BlockItem.getBlockEntityData(banner);
        CompoundTag tag = bannerTag == null ? new CompoundTag() : bannerTag.copy();
        tag.putInt("Base", ((BannerItem) banner.getItem()).getColor().getId());
        
        BlockItem.setBlockEntityData(shield, BlockEntityType.BANNER, tag);
        return shield;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AstralRecipes.SHIELD_DECORATION.get(); // Your registered serializer
    }
}