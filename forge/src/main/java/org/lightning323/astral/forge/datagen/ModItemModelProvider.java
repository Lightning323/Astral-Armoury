package org.lightning323.astral.forge.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.registries.AstralItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AstralArmoury.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistrySupplier<Item> item : AstralItems.basicItems) {
            if (!hasCustomModel(item)) basicItem(item.get());
        }
        for (RegistrySupplier<Item> item : AstralItems.tools) {
            if (!hasCustomModel(item)) handheldItem(item);
        }
        for (RegistrySupplier<Item> item : AstralItems.armor) {
            if (!hasCustomModel(item)) simpleArmorItem(item);
        }
        for (RegistrySupplier<Item> item : AstralItems.shields) {
            if (!hasCustomModel(item)) shieldItem(item);
        }
    }

    private ResourceLocation itemResource(RegistrySupplier<Item> item) {
        ResourceLocation texture = modLoc("item/" + item.getId().getPath());
        String resource = AstralItems.customTextureLocations.get(item);
        if (resource != null) {
            texture = modLoc("item/" + resource);
        }
        return texture;
    }


    private void shieldItem(RegistrySupplier<Item> shield) {
        String name = shield.getId().getPath();

        // 1. The "Blocking" Model (The 3D model shown when right-clicking)
        // Parent "minecraft:item/shield_blocking" handles the 3D position
        ModelFile blockingModel = getBuilder(name + "_blocking")
                .parent(getExistingFile(mcLoc("item/shield_blocking")))
                .texture("particle", modLoc("item/" + name));

        // 2. The "Base" Model (The 3D model shown when just held)
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/shield")))
                .texture("particle", modLoc("item/" + name))
                // This "override" tells Minecraft: "If the 'blocking' property is 1, use the blocking model"
                .override()
                .predicate(new ResourceLocation("blocking"), 1)
                .model(blockingModel)
                .end();
    }

    // Helper method to make armor registration cleaner
    private void simpleArmorItem(RegistrySupplier<Item> item) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0",
                        itemResource(item)
                );
    }

    private boolean hasCustomModel(RegistrySupplier<Item> item) {
        String name = item.getId().getPath();
        ResourceLocation customModelLoc = modLoc("item/" + name);
        return existingFileHelper.exists(customModelLoc, net.minecraft.server.packs.PackType.CLIENT_RESOURCES, ".json", "models");
    }

    private void handheldItem(RegistrySupplier<Item> item) {
        String name = item.getId().getPath();
        withExistingParent(name, mcLoc("item/handheld"))
                .texture("layer0", itemResource(item));
    }


}