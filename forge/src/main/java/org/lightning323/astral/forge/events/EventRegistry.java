package org.lightning323.astral.forge.events;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.lightning323.nexus.potions.PotionsRegistry;

public class EventRegistry {
    public static void setup(final FMLCommonSetupEvent event) {
        PotionsRegistry.setup();
    }
}
