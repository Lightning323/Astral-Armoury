package org.lightning323.astral.fabric;

import net.fabricmc.api.ModInitializer;
import org.lightning323.astral.AstralArmoury;

public final class AstralArmouryFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        AstralArmoury.init();
    }

}
