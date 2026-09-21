package org.lightning323.astral.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.lightning323.astral.Astral;

/**
 * Renders the wildfire trident exactly like the vanilla trident's BEWLR path:
 * the flat item sprite for GUI/ground/fixed contexts and the 3D
 * {@link TridentModel} (skinned with the unique wildfire texture) everywhere
 * else. The charge pose comes from the {@code in_hand} item model's display
 * transforms plus the vanilla {@code ItemInHandRenderer} spear animation, so
 * it matches the vanilla trident identically.
 */
public class WildfireTridentItemRenderer extends BlockEntityWithoutLevelRenderer {
    private static final ResourceLocation TEXTURE = Astral.resource("textures/entity/wildfire_trident.png");

    private TridentModel tridentModel;

    public WildfireTridentItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poses,
                             MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (context == ItemDisplayContext.GUI || context == ItemDisplayContext.GROUND || context == ItemDisplayContext.FIXED) {
            renderFlatSprite(stack, context, poses, buffer, packedLight, packedOverlay);
            return;
        }
        if (this.tridentModel == null) {
            this.tridentModel = new TridentModel(
                    Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.TRIDENT));
        }
        poses.pushPose();
        poses.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer consumer = ItemRenderer.getFoilBufferDirect(
                buffer, this.tridentModel.renderType(TEXTURE), false, stack.hasFoil());
        this.tridentModel.renderToBuffer(poses, consumer, packedLight, packedOverlay);
        poses.popPose();
    }

    private void renderFlatSprite(ItemStack stack, ItemDisplayContext context, PoseStack poses,
                                  MultiBufferSource buffer, int packedLight, int packedOverlay) {
        BakedModel flat = Minecraft.getInstance().getModelManager().getModel(
                ModelResourceLocation.inventory(Astral.resource("wildfire_trident")));
        Minecraft.getInstance().getItemRenderer().render(stack, context, false, poses, buffer,
                packedLight, packedOverlay, flat);
    }
}