package org.lightning323.astral.forge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        basicItem(ModItems.CLAYMORE.get());
    }
}