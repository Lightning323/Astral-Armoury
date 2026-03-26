package org.lightning323.astral.forge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.lightning323.astral.AstralArmoury;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        // This tells Forge which mod and locale to generate for
        super(output, AstralArmoury.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        AstralArmoury.translations.forEach(this::add);
        AstralArmoury.itemTranslations.forEach(this::addItem);

        add("itemGroup." + AstralArmoury.MOD_ID + ".main", "Astral Armoury");
        add("tooltip.astral.heavy", "Slow but deadly.");
    }
}