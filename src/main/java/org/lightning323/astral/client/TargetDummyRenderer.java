package org.lightning323.astral.client;

import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.lightning323.astral.Astral;

/**
 * Renderer for the training dummy: vanilla armor-stand model with the Astral
 * dummy texture.
 */
public class TargetDummyRenderer extends ArmorStandRenderer {
    private static final ResourceLocation TEXTURE = Astral.resource("textures/entity/target_dummy.png");

    public TargetDummyRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ArmorStand entity) {
        return TEXTURE;
    }
}
