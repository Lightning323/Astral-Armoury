package org.lightning323.astral.forge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lightning323.astral.AstralArmoury;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = AstralArmoury.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Add Providers
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(output));

        // Language Provider (Optional but recommended)
         generator.addProvider(event.includeClient(), new ModLanguageProvider(output));

        generator.addProvider(event.includeServer(), new ModTagProvider(
                generator.getPackOutput(),           // 1. PackOutput
                Registries.ITEM,                     // 2. ResourceKey (The "Item" Registry)
                event.getLookupProvider(),           // 3. CompletableFuture
                event.getExistingFileHelper()        // 4. ExistingFileHelper
        ));
    }
}