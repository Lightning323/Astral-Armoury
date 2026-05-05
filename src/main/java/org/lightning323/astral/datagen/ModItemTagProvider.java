package org.lightning323.astral.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;
import org.lightning323.astral.registries.AstralItems;

import java.util.concurrent.CompletableFuture;

import static org.lightning323.astral.Astral.MODID;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Trimmable Armor
        var trimmableBuilder = this.tag(ItemTags.TRIMMABLE_ARMOR);
        for (DeferredHolder<Item, ? extends Item> armorItem : AstralItems.armor) {
            // .getKey() returns the ResourceKey<Item>, which is still correct for .add()
            trimmableBuilder.add(armorItem.getKey());
        }

        // Shields - Moving from "forge:shields" to "c:shields"
        // In 1.21, use ResourceLocation.fromNamespaceAndPath or ResourceLocation.parse
        TagKey<Item> commonShields = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "shields"));

        var shieldBuilder = this.tag(commonShields);
        for (DeferredHolder<Item, ? extends Item> shieldItem : AstralItems.shields) {
            shieldBuilder.add(shieldItem.getKey());
        }
    }
}