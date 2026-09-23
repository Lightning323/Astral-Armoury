package org.lightning323.astral.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.ThrownTrident;
import org.lightning323.astral.Astral;

/**
 * Thrown-entity renderer for the molten trident: vanilla trident model and
 * flight behaviour with the unique molten texture.
 */
public class MoltenTridentRenderer extends ThrownTridentRenderer {
    private static final ResourceLocation TEXTURE = Astral.resource("textures/entity/molten_trident.png");

    public MoltenTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownTrident entity) {
        return TEXTURE;
    }
}
