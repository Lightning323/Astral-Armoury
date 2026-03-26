package org.lightning323.astral.shield;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.ItemStack;

public class AstralShieldRenderer extends BlockEntityWithoutLevelRenderer {
    private static ShieldModel SHIELD_MODEL;

    public AstralShieldRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
    }

    public static void render(ItemStack stack, PoseStack ps, MultiBufferSource buffer, int light, int overlay) {
        // Initialize model if null (Architectury doesn't provide the EMS directly here)
        if (SHIELD_MODEL == null) {
            SHIELD_MODEL = new ShieldModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SHIELD));
        }

        ps.pushPose();
        // Minecraft shield rendering requires a specific flip
        ps.scale(1.0F, -1.0F, -1.0F);

        boolean isBanner = (stack.getTagElement("BlockEntityTag") != null);
        Material renderMaterial = MaterialShieldRegistry.getMaterial(stack, isBanner);

        // Architectury/Vanilla friendly vertex consumer
        VertexConsumer vertex = renderMaterial.sprite().wrap(ItemRenderer.getFoilBufferDirect(
                buffer,
                RenderType.entityCutoutNoCull(renderMaterial.atlasLocation()),
                true,
                stack.hasFoil()
        ));

        // Render Handle and Plate
        SHIELD_MODEL.handle().render(ps, vertex, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        SHIELD_MODEL.plate().render(ps, vertex, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);

        ps.popPose();
    }
}