package org.lightning323.astral;

import com.google.common.base.Suppliers;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.lightning323.astral.registries.ModCreativeModeTabs;
import org.lightning323.astral.registries.ModItems;
import org.lightning323.astral.registries.SoundRegistry;
import org.lightning323.astral.shield.AstralShieldRenderer;
import org.lightning323.astral.shield.MaterialShieldRegistry;
import org.apache.logging.log4j.Logger;
import java.util.function.Supplier;

public final class AstralArmoury {
    public static final String MOD_ID = "astral";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    // Memoize ensures this is only calculated once and then cached
    public static final Supplier<RegistrarManager> REGISTRIES =
            Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static void init() {
        ModCreativeModeTabs.register();
        ModItems.register();
        SoundRegistry.register();
    }

    public static ResourceLocation resource(String emeraldBoots) {
        return new ResourceLocation(MOD_ID, emeraldBoots);
    }

    public static AstralShieldRenderer shieldRenderer;

    public static void setupClient() {
        MaterialShieldRegistry.init();
    }
}
