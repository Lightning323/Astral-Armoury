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
import org.lightning323.astral.registries.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AstralArmoury.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // This creates: assets/astral_armoury/models/item/claymore.json
        // It assumes your texture is at: assets/astral_armoury/textures/item/claymore.png
//        basicItem(ModItems.CLAYMORE.get());
        withExistingParent(ModItems.CLAYMORE.getId().getPath(), "item/handheld")
                .texture("layer0", modLoc("item/" + ModItems.CLAYMORE.getId().getPath()));
        // Inside registerModels()
        withExistingParent(ModItems.EMERALD_AXE.getId().getPath(), "item/handheld")
                .texture("layer0", modLoc("item/" + ModItems.EMERALD_AXE.getId().getPath()));
        withExistingParent(ModItems.EMERALD_SWORD.getId().getPath(), "item/handheld")
                .texture("layer0", modLoc("item/" + ModItems.EMERALD_SWORD.getId().getPath()));
        withExistingParent(ModItems.EMERALD_PICKAXE.getId().getPath(), "item/handheld")
                .texture("layer0", modLoc("item/" + ModItems.EMERALD_PICKAXE.getId().getPath()));
        withExistingParent(ModItems.EMERALD_SHOVEL.getId().getPath(), "item/handheld")
                .texture("layer0", modLoc("item/" + ModItems.EMERALD_SHOVEL.getId().getPath()));
        withExistingParent(ModItems.EMERALD_HOE.getId().getPath(), "item/handheld")
                .texture("layer0", modLoc("item/" + ModItems.EMERALD_HOE.getId().getPath()));

        simpleArmorItem(ModItems.EMERALD_HELMET);
        simpleArmorItem(ModItems.EMERALD_CHESTPLATE);
        simpleArmorItem(ModItems.EMERALD_LEGGINGS);
        simpleArmorItem(ModItems.EMERALD_BOOTS);

        shieldItem(ModItems.SHIELD_LEATHER);
        shieldItem(ModItems.SHIELD_OBSIDIAN);
        shieldItem(ModItems.SHIELD_NETHERITE);

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