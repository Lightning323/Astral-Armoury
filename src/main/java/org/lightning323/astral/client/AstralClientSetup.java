package org.lightning323.astral.client;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.lightning323.astral.Astral;
import org.lightning323.astral.item.neko.SlingshotItem;
import org.lightning323.astral.registries.AstralEntities;
import org.lightning323.astral.registries.AstralItems;

/**
 * Client registration for Neko-ported content: entity renderers and the
 * slingshot draw-stage item properties.
 */
@EventBusSubscriber(modid = Astral.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AstralClientSetup {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(AstralEntities.SLINGSHOT_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(AstralEntities.MOLTEN_TRIDENT.get(), MoltenTridentRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Keys mirror vanilla's bow ("pull"/"pulling" parse to the minecraft namespace).
            ItemProperties.register(AstralItems.SLINGSHOT.get(), ResourceLocation.parse("pull"),
                    (stack, level, entity, seed) -> {
                        if (entity == null || entity.getUseItem() != stack) {
                            return 0.0F;
                        }
                        return SlingshotItem.getPullProgress(
                                stack.getUseDuration(entity) - entity.getUseItemRemainingTicks());
                    });
            ItemProperties.register(AstralItems.SLINGSHOT.get(), ResourceLocation.parse("pulling"),
                    (stack, level, entity, seed) ->
                            entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
            // Mirrors vanilla's trident "throwing" property: 1.0 while charging so the
            // model swaps to the in-hand charge pose exactly like the trident does.
            ItemProperties.register(AstralItems.MOLTEN_TRIDENT.get(), ResourceLocation.parse("throwing"),
                    (stack, level, entity, seed) ->
                            entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
        });
    }
}
