package org.lightning323.astral.forge.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.registries.AstralItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AstralArmoury.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistrySupplier<Item> basicItem : AstralItems.basicItems) {
            basicItem(basicItem.get());
        }

        for (RegistrySupplier<Item> tool : AstralItems.tools) {
            withExistingParent(tool.getId().getPath(), "item/handheld")
                    .texture("layer0", modLoc("item/" + tool.getId().getPath()));
        }

        for (RegistrySupplier<Item> armor : AstralItems.armor) {
            simpleArmorItem(armor);
        }

        for (RegistrySupplier<Item> shield : AstralItems.shields) {
            shieldItem(shield);
        }
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
                        new ResourceLocation(AstralArmoury.MOD_ID, "item/" + item.getId().getPath()));
    }

    private void handheldItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld"))
                .texture("layer0",
                        new ResourceLocation(AstralArmoury.MOD_ID, "item/" + item.getId().getPath()));
    }
}