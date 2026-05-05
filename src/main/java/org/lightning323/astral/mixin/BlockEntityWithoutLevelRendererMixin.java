package org.lightning323.astral.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.lightning323.astral.item.shield.AstralShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin {

    @Shadow
    private ShieldModel shieldModel;


    @Inject(
            method = "renderByItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void myMod$renderCustomShield(ItemStack itemStack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, CallbackInfo ci) {
        if (itemStack.getItem() instanceof AstralShieldItem shield) {//itemStack.is(ModItems.SHIELD_LEATHER.get())
            render(shield, itemStack, poseStack, buffer, packedLight, packedOverlay);
            ci.cancel();
        }
    }

    private void render(AstralShieldItem shield, ItemStack p_108830_, PoseStack p_108832_, MultiBufferSource p_108833_, int p_108834_, int p_108835_) {
        BannerPatternLayers bannerpatternlayers = (BannerPatternLayers) p_108830_.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
        DyeColor dyecolor = (DyeColor) p_108830_.get(DataComponents.BASE_COLOR);
        boolean flag = !bannerpatternlayers.layers().isEmpty() || dyecolor != null;
        p_108832_.pushPose();
        p_108832_.scale(1.0F, -1.0F, -1.0F);

        Material material = flag ? shield.getBaseMaterial() : shield.getNoPatternMaterial();
//        Material material = flag ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;

        VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(p_108833_, shieldModel.renderType(material.atlasLocation()), true, p_108830_.hasFoil()));
        shieldModel.handle().render(p_108832_, vertexconsumer, p_108834_, p_108835_);
        if (flag) {
            BannerRenderer.renderPatterns(p_108832_, p_108833_, p_108834_, p_108835_, shieldModel.plate(), material, false, (DyeColor) Objects.requireNonNullElse(dyecolor, DyeColor.WHITE), bannerpatternlayers, p_108830_.hasFoil());
        } else {
            shieldModel.plate().render(p_108832_, vertexconsumer, p_108834_, p_108835_);
        }

        p_108832_.popPose();
    }
}