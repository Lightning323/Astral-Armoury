package org.lightning323.astral.registries;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.lightning323.astral.AstralArmoury;

public class ModCreativeModeTabs {
    // 1. Grab the Registrar from your common Manager
    public static final Registrar<CreativeModeTab> TABS =
            AstralArmoury.REGISTRIES.get().get(Registries.CREATIVE_MODE_TAB);

    // 2. Wrap the registration in a static field so it's accessible and runs on class load
    public static final RegistrySupplier<CreativeModeTab> ASTRAL_TAB = TABS.register(
            new ResourceLocation(AstralArmoury.MOD_ID, "astral_tab"),
            () -> CreativeTabRegistry.create(
                    Component.translatable("itemGroup." + AstralArmoury.MOD_ID + ".test_tab"),
                    () -> new ItemStack(ModItems.CLAYMORE.get())
            )
    );

    // 3. Keep the register method to "kick" the class into loading
    public static void register() {
    }
}