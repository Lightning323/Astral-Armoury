package org.lightning323.astral.item.neko;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.lightning323.astral.entity.neko.TargetDummyEntity;
import org.lightning323.astral.registries.AstralEntities;

/**
 * Training-dummy spawner ported from Neko's {@code TargetDummyItem}.
 */
public class TargetDummyItem extends Item {
    public TargetDummyItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Direction direction = context.getClickedFace();
        if (direction == Direction.DOWN) return InteractionResult.FAIL;
        Level level = context.getLevel();
        BlockPlaceContext placeContext = new BlockPlaceContext(context);
        BlockPos pos = placeContext.getClickedPos();
        ItemStack stack = context.getItemInHand();
        Vec3 center = Vec3.atBottomCenterOf(pos);
        AABB box = AstralEntities.TARGET_DUMMY.get().getDimensions()
                .makeBoundingBox(center.x(), center.y(), center.z());
        if (level.noCollision(null, box) && level.getEntities(null, box).isEmpty()) {
            if (level instanceof ServerLevel serverLevel) {
                TargetDummyEntity dummy = AstralEntities.TARGET_DUMMY.get().create(serverLevel);
                if (dummy == null) return InteractionResult.FAIL;
                float yaw = Mth.floor((Mth.wrapDegrees(context.getRotation() - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                dummy.moveTo(center.x(), center.y(), center.z(), yaw, 0.0F);
                dummy.setShowArms(true);
                dummy.setNoBasePlate(true);
                serverLevel.addFreshEntityWithPassengers(dummy);
                serverLevel.playSound(null, dummy.getX(), dummy.getY(), dummy.getZ(),
                        SoundEvents.ARMOR_STAND_PLACE, SoundSource.BLOCKS, 0.75F, 0.8F);
                dummy.gameEvent(GameEvent.ENTITY_PLACE, context.getPlayer());
                stack.shrink(1);
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.FAIL;
    }
}
