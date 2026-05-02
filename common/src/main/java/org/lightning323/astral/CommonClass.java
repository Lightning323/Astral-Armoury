package org.lightning323.astral;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.lightning323.astral.item.shield.ShieldMaterialHandler;
import org.lightning323.astral.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import org.lightning323.astral.registries.*;
import org.slf4j.Logger;

import java.util.HashMap;


public class CommonClass {
    public static final String MOD_ID = "astral";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static Platform PLATFORM;

    public static final HashMap<String, String> translations = new HashMap<>();
    public static final HashMap<Supplier<Item>, String> itemTranslations = new HashMap<>();

    // Memoize ensures this is only calculated once and then cached
    public static final Supplier<RegistrarManager> REGISTRIES =
            Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));


    public static void init() {

        if (Services.PLATFORM.isModLoaded("astral_armoury")) {
//            Constants.LOG.info("Hello to astral_armoury");
        }

        ModCreativeModeTabs.register();
        AstralItems.register();
        AstralSounds.register();
        AstralRecipes.register();
        AstralBlocks.register();

        // Inside your initialization method
        InteractionEvent.LEFT_CLICK_BLOCK.register((player, hand, pos, direction) -> {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.getItem() instanceof LightningStaffItem staff) {
                if (!player.level().isClientSide()) {
                    staff.summonLightning(player.level(), pos, player);
                    return EventResult.interruptTrue();
                }
            }
            return EventResult.pass();
        });
    }


    public static void setupClient() {
        ShieldMaterialHandler.init();
    }

    public static ResourceLocation resource(String emeraldBoots) {
        return new ResourceLocation(MOD_ID, emeraldBoots);
    }
}
