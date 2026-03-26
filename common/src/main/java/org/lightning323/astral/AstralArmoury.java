package org.lightning323.astral;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.lightning323.astral.registries.*;
import org.lightning323.astral.item.shield.ShieldMaterialHandler;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.function.Supplier;

public final class AstralArmoury {
    public static final String MOD_ID = "astral";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static Platform PLATFORM;

    public static final HashMap<String,String> translations = new HashMap<>();
    public static final HashMap<Supplier<Item>,String> itemTranslations = new HashMap<>();

    // Memoize ensures this is only calculated once and then cached
    public static final Supplier<RegistrarManager> REGISTRIES =
            Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));



    public static void init() {
        ModCreativeModeTabs.register();
        AstralItems.register();
        AstralSounds.register();
        AstralRecipes.register();
        AstralBlocks.register();
    }

    public static void setupClient() {
        ShieldMaterialHandler.init();
    }

    public static ResourceLocation resource(String emeraldBoots) {
        return new ResourceLocation(MOD_ID, emeraldBoots);
    }
}
