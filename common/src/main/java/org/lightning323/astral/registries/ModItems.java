package org.lightning323.astral.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import org.lightning323.astral.AstralArmoury;

import static org.lightning323.astral.AstralArmoury.MOD_ID;

public class ModItems {
    //https://docs.architectury.dev/api/registry

    public static final Registrar<Item> ITEMS = AstralArmoury.REGISTRIES.get().get(Registries.ITEM);

    public static final RegistrySupplier<Item> CLAYMORE = ITEMS.register(
            new ResourceLocation(MOD_ID, "claymore"),
            () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB))
    );

    // 3. The init method called by platform-specific loaders
    public static void register() {

    }
}