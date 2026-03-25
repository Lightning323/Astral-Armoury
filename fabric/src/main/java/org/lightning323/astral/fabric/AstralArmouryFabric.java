package org.lightning323.astral.fabric;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import org.lightning323.astral.AstralArmoury;
import net.fabricmc.api.ModInitializer;

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
