package org.lightning323.astral.forge;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import org.lightning323.astral.AstralArmoury;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lightning323.astral.PlatformUtils;

@Mod(AstralArmoury.MOD_ID)
public final class AstralArmouryForge {
    private static final Registrate FORGE_REGISTRATE = Registrate.create(AstralArmoury.MOD_ID);

    public AstralArmouryForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(AstralArmoury.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        AstralArmoury.init();
    }

    public static final ItemEntry<Item> SNOWFLAKE = FORGE_REGISTRATE.item("snowflake", Item::new).register();
}
