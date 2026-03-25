package org.lightning323.astral;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import org.lightning323.astral.registries.ModCreativeModeTabs;
import org.lightning323.astral.registries.ModItems;

import java.util.function.Supplier;

public final class AstralArmoury {
    public static final String MOD_ID = "astral";

    // Memoize ensures this is only calculated once and then cached
    public static final Supplier<RegistrarManager> REGISTRIES =
            Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static void init() {
        ModCreativeModeTabs.register();
        ModItems.register();
    }
}
