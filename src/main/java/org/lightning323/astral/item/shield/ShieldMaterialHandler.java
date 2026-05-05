package org.lightning323.astral.item.shield;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.lightning323.astral.registries.AstralItems;

import java.util.function.Supplier;

import static org.lightning323.astral.Astral.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ShieldMaterialHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (Supplier<? extends Item> shield : AstralItems.shields) {
                ItemProperties.register(
                        shield.get(),
                        // Ensure AstralShieldItem.BLOCKING is a ResourceLocation e.g., ResourceLocation.withDefaultNamespace("blocking")
                        AstralShieldItem.BLOCKING,
                        (stack, world, entity, seed) ->
                                entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
                );
            }
        });
    }
}