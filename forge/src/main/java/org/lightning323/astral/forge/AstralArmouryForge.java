package org.lightning323.astral.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.forge.effects.EffectRegistry;

@Mod(AstralArmoury.MOD_ID)
public final class AstralArmouryForge {

    static {
        AstralArmoury.PLATFORM = new PlatformForge();
    }

    public AstralArmouryForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(AstralArmoury.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // 3. Add listeners for lifecycle events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::setupClient);

        // Run our common setup.
        AstralArmoury.init();

        PotionsRegistry.register(modEventBus);
        EffectRegistry.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // 4. Run "Setup" logic (like Brewing Recipes) inside the work queue
        event.enqueueWork(() -> {
            PotionsRegistry.setup();
        });
    }

    public void setupClient(final FMLClientSetupEvent event) {
        AstralArmoury.setupClient();
    }

    @Mod.EventBusSubscriber(modid = AstralArmoury.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    static class ClientForgeEvents {
    }


}
