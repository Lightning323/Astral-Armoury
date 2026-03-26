package org.lightning323.astral.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lightning323.astral.AstralArmoury;

@Mod(AstralArmoury.MOD_ID)
public final class AstralArmouryForge {

    public AstralArmouryForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(AstralArmoury.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AstralArmouryForge::setupClient);

        // Run our common setup.
        AstralArmoury.init();
    }

    public static void setupClient(final FMLClientSetupEvent event) {
        AstralArmoury.setupClient();
    }

}
