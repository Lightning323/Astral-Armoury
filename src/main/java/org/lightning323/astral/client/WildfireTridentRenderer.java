package org.lightning323.astral.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.ThrownTrident;
import org.lightning323.astral.Astral;

/**
 * Thrown-entity renderer for the wildfire trident: vanilla trident model and
 * flight behaviour with the unique wildfire texture.
 */
public class WildfireTridentRenderer extends ThrownTridentRenderer {
    private static final ResourceLocation TEXTURE = Astral.resource("textures/entity/wildfire_trident.png");

    public WildfireTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownTrident entity) {
        return TEXTURE;
    }
}
