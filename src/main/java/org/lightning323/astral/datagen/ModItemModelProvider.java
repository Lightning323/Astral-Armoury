package org.lightning323.astral.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.lightning323.astral.Astral;
import org.lightning323.astral.registries.AstralItems;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Astral.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Using DeferredHolder (the NeoForge equivalent of RegistrySupplier)
        for (DeferredHolder<Item, ? extends Item> item : AstralItems.basicItems) {
            if (!hasCustomModel(item)) basicItem(item.get());
        }
        for (DeferredHolder<Item, ? extends Item> item : AstralItems.tools) {
            if (!hasCustomModel(item)) handheldItem(item);
        }
        for (DeferredHolder<Item, ? extends Item> item : AstralItems.armor) {
            if (!hasCustomModel(item)) simpleArmorItem(item);
        }
        for (DeferredHolder<Item, ? extends Item> item : AstralItems.shields) {
            if (!hasCustomModel(item)) shieldItem(item);
        }
    }

    private ResourceLocation itemResource(DeferredHolder<Item, ? extends Item> item) {
        ResourceLocation texture = modLoc("item/" + item.getId().getPath());
        String resource = AstralItems.customTextureLocations.get(item);
        if (resource != null) {
            texture = modLoc("item/" + resource);
        }
        return texture;
    }

    private void shieldItem(DeferredHolder<Item, ? extends Item> shield) {
        String name = shield.getId().getPath();

        // 1. The "Blocking" Model
        ModelFile blockingModel = getBuilder(name + "_blocking")
                .parent(getExistingFile(mcLoc("item/shield_blocking")))
                .texture("particle", modLoc("item/" + name));

        // 2. The "Base" Model
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/shield")))
                .texture("particle", modLoc("item/" + name))
                // In 1.21, predicates use ResourceLocation.parse or .fromNamespaceAndPath
                .override()
                .predicate(ResourceLocation.withDefaultNamespace("blocking"), 1)
                .model(blockingModel)
                .end();
    }

    private void simpleArmorItem(DeferredHolder<Item, ? extends Item> item) {
        // changed to ResourceLocation.withDefaultNamespace for "item/generated"
        withExistingParent(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0", itemResource(item));
    }

    private boolean hasCustomModel(DeferredHolder<Item, ? extends Item> item) {
        String name = item.getId().getPath();
        ResourceLocation customModelLoc = modLoc("item/" + name);
        // PackType is now often accessed via the helper or constants
        return existingFileHelper.exists(customModelLoc, net.minecraft.server.packs.PackType.CLIENT_RESOURCES, ".json", "models");
    }

    private void handheldItem(DeferredHolder<Item, ? extends Item> item) {
        String name = item.getId().getPath();
        withExistingParent(name, mcLoc("item/handheld"))
                .texture("layer0", itemResource(item));
    }
}