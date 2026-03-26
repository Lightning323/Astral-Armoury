package org.lightning323.astral.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.*;
import org.lightning323.astral.shield.AstralShieldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
        if (itemStack.getItem() instanceof ShieldItem) {//itemStack.is(ModItems.SHIELD_LEATHER.get())
            AstralShieldRenderer.render(shieldModel, itemStack, poseStack, buffer, packedLight, packedOverlay);
            ci.cancel();
        }
    }
}