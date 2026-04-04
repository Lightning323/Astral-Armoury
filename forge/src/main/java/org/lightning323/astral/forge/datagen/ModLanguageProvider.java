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
        add("item.minecraft.potion.effect.astral_reach_distance","Potion of Reach Distance");
        add("item.minecraft.tipped_arrow.effect.astral_reach_distance","Arrow of Reach Distance");

        add("item.minecraft.potion.effect.astral_blind","Potion of Blindness");
        add("item.minecraft.tipped_arrow.effect.astral_blind","Arrow of Blindness");

        add("item.minecraft.potion.effect.astral_freeze","Potion of Freeze");
        add("item.minecraft.tipped_arrow.effect.astral_freeze","Arrow of Freeze");

        add("item.minecraft.potion.effect.astral_attack_range","Potion of Attack Range");
        add("item.minecraft.tipped_arrow.effect.astral_attack_range","Arrow of Attack Range");
    }
}