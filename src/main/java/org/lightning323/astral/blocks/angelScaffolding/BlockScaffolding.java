package org.lightning323.astral.blocks.angelScaffolding;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockScaffolding extends Block {

    private static final double CHANCE_CRUMBLE = 0.6;
    private static final double CLIMB_SPEED = 0.31D;
    private static final double OFFSET = 2.0D; // Block.box uses 0-16 scale
    public static final VoxelShape AABB = Block.box(OFFSET, OFFSET, OFFSET,
            16.0D - OFFSET, 16.0D - OFFSET, 16.0D - OFFSET);

    private final boolean doesAutobreak;

    public BlockScaffolding( boolean autobreak) {
        super(Block.Properties.of().strength(0.1F).randomTicks().noOcclusion().sound(SoundType.SCAFFOLDING));
        this.doesAutobreak = autobreak;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        // If a block of the same type next to it was broken, destroy this one (Chain reaction)
        if (neighborBlock == this && level.getBlockState(neighborPos).isAir()) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (this.doesAutobreak && random.nextDouble() < CHANCE_CRUMBLE) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.isShiftKeyDown() || stack.isEmpty()) {
            return ItemInteractionResult.CONSUME;
        }

        Block heldBlock = Block.byItem(stack.getItem());

        // Prevent infinite loop if trying to place more scaffolding
        if (heldBlock != Blocks.AIR && !(heldBlock instanceof BlockScaffolding)) {
            if (!level.isClientSide) {
                level.destroyBlock(pos, true);

                // Create a use context to place the new block where the scaffolding was
                UseOnContext context = new UseOnContext(player, hand, hit);
                InteractionResult result = stack.useOn(context);

                if (result.consumesAction()) {
                    return ItemInteractionResult.SUCCESS;
                }
            } else {
                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.CONSUME;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            // Horizontal collision check like ladders/vines
            if (entity.horizontalCollision) {
                tryMakeEntityClimb(livingEntity, CLIMB_SPEED);
            }
        }
    }

    public static void tryMakeEntityClimb(LivingEntity entity, double climbSpeed) {
        if (entity.isShiftKeyDown()) {
            entity.setDeltaMovement(entity.getDeltaMovement().x, 0.0, entity.getDeltaMovement().z);
        } else if (entity.zza > 0.0F && entity.getDeltaMovement().y < climbSpeed) {
            entity.setDeltaMovement(entity.getDeltaMovement().x, climbSpeed, entity.getDeltaMovement().z);
            entity.resetFallDistance(); // fallDistance = 0.0F in 1.21.1
        }
    }
}