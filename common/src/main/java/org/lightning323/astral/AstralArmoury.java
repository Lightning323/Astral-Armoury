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

    public static void setupClient() {
        MaterialShieldRegistry.init();


            // 1. Register Item Properties (for the pulling animation)
            ItemPropertiesRegistry.register(ModItems.SHIELD_LEATHER.get(), new ResourceLocation("pull"),
                    (stack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

            ItemPropertiesRegistry.register(ModItems.SHIELD_LEATHER.get(), new ResourceLocation("pulling"),
                    (stack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

//
//          // This makes sure the shield isn't "empty" or a flat white square
//            ItemRendererRegistry.registerRenderer(ModItems.SHIELD_LEATHER.get(), (stack, matrices, vertexConsumer, light, overlay) -> {
//                // You call your custom shield renderer here
//                AstralShieldRenderer.render(stack, matrices, vertexConsumer, light, overlay);
//            });


    }

    public static AstralShieldRenderer shieldRenderer;

    public static void onRegisterReloadListener() {


    }
}
