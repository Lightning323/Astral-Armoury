package org.lightning323.astral.shield;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Holder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.List;

@Environment(EnvType.CLIENT)
public class AstralShieldRenderer extends BlockEntityWithoutLevelRenderer {
//    private static ShieldModel shieldModel = new ShieldModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SHIELD));

    public AstralShieldRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
    }

    public static void render(ShieldModel shieldModel, ItemStack itemStack, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        boolean bl = BlockItem.getBlockEntityData(itemStack) != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = MaterialShieldRegistry.getMaterial(itemStack, bl); //Shield base vs nopattern shield

        VertexConsumer vertexConsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(
                multiBufferSource,
                RenderType.entityCutoutNoCull(material.atlasLocation()),//shieldModel.renderType(material.atlasLocation()),
                true,
                itemStack.hasFoil()
        ));
        shieldModel.handle().render(poseStack, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        if (bl) {
            List<Pair<Holder<BannerPattern>, DyeColor>> list = BannerBlockEntity.createPatterns(ShieldItem.getColor(itemStack), BannerBlockEntity.getItemPatterns(itemStack));
            BannerRenderer.renderPatterns(poseStack, multiBufferSource, light, overlay, shieldModel.plate(), material, false, list, itemStack.hasFoil());
        } else {
            shieldModel.plate().render(poseStack, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        }

        poseStack.popPose();
    }
}