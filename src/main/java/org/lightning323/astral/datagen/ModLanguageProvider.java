package org.lightning323.astral.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.lightning323.astral.Astral;


public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output) {
        // Updated to use NeoForge's LanguageProvider
        // The locale "en_us" and MOD_ID parameters remain the same
        super(output, Astral.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Assuming your translation maps contain the appropriate RegistryHolders/Items
        Astral.translations.forEach(this::addItem);
        Astral.itemTranslations.forEach(this::addItem);

        // Standard string-based translations
        add("itemGroup." + Astral.MODID + ".main", "Astral Armoury");
        add("tooltip.astral.heavy", "Slow but deadly.");

        // Potion and Arrow effect translations
        add("item.minecraft.potion.effect.astral_reach_distance", "Potion of Reach Distance");
        add("item.minecraft.tipped_arrow.effect.astral_reach_distance", "Arrow of Reach Distance");

        add("item.minecraft.potion.effect.astral_blind", "Potion of Blindness");
        add("item.minecraft.tipped_arrow.effect.astral_blind", "Arrow of Blindness");

        add("item.minecraft.potion.effect.astral_freeze", "Potion of Freeze");
        add("item.minecraft.tipped_arrow.effect.astral_freeze", "Arrow of Freeze");

        add("item.minecraft.potion.effect.astral_attack_range", "Potion of Attack Range");
        add("item.minecraft.tipped_arrow.effect.astral_attack_range", "Arrow of Attack Range");
    }
}