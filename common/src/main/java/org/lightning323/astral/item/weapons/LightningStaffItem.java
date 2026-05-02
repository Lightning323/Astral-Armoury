package org.lightning323.astral.item.weapons;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LightningStaffItem extends Item {

    final static int cooldownSec = 5;

    public LightningStaffItem(Properties properties) {
        super(properties);
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
//        ItemStack itemstack = player.getItemInHand(interactionHand);
//        BlockHitResult raytraceresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
//
//        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
//            if (raytraceresult.getType() == HitResult.Type.BLOCK || raytraceresult.getType() == HitResult.Type.ENTITY) {
//                BlockPos pos = raytraceresult.getBlockPos();
//                summonLightning(level, pos, player);
//                player.getCooldowns().addCooldown(this, 20 * cooldownSec);
//
//                return InteractionResultHolder.success(itemstack);
//            }
//        }
//
//        return InteractionResultHolder.pass(itemstack);
//    }


    public void summonLightning(Level level, BlockPos pos, LivingEntity attacker) {
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
            if (bolt != null) {
                bolt.moveTo(Vec3.atBottomCenterOf(pos));

                // Safety: If the player is too close, make it visual-only
                // so they don't die from their own power.
                if (attacker.distanceToSqr(Vec3.atCenterOf(pos)) < 9.0D) {
                    bolt.setVisualOnly(true);
                }

                if (attacker instanceof ServerPlayer sPlayer) {
                    bolt.setCause(sPlayer);
                }

                serverLevel.addFreshEntity(bolt);
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.summonLightning(attacker.level(), target.blockPosition(), attacker);
        return super.hurtEnemy(stack, target, attacker);
    }
}