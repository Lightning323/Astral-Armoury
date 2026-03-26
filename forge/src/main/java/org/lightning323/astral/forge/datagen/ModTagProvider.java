package org.lightning323.astral.forge.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.registries.AstralItems;

import java.util.concurrent.CompletableFuture;

public class ModTagProvider extends TagsProvider<Item> {

    protected ModTagProvider(PackOutput packOutput,
                             ResourceKey<? extends Registry<Item>> lookupProvider,
                             CompletableFuture<HolderLookup.Provider> completableFuture,
                             @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, completableFuture, AstralArmoury.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        var trimmableBuilder = this.tag(ItemTags.TRIMMABLE_ARMOR);
        for (RegistrySupplier<Item> armorItem : AstralItems.armor) {
            trimmableBuilder.add(armorItem.getKey());
        }

        for (RegistrySupplier<Item> shieldItem : AstralItems.shields) {
            this.tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "shields")))
                    .add(shieldItem.getKey());
        }
    }
}
