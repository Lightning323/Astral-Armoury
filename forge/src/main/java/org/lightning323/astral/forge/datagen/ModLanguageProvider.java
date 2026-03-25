package org.lightning323.astral.forge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.registries.ModItems;
import org.lightning323.astral.registries.ModCreativeModeTabs;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        // This tells Forge which mod and locale to generate for
        super(output, AstralArmoury.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Items
        addItem(ModItems.CLAYMORE, "Claymore");
        
        // Creative Tabs (The key must match your Component.translatable)
        add("itemGroup." + AstralArmoury.MOD_ID + ".test_tab", "Astral Armoury");
        
        // You can also add generic strings or subtitles
        add("tooltip.astral.heavy", "Slow but deadly.");
    }
}