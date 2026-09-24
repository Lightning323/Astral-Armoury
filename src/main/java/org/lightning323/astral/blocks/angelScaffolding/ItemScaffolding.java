package org.lightning323.astral.blocks.angelScaffolding;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ItemScaffolding extends BlockItem {

    public ItemScaffolding(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
        if (player.isCrouching()) {    //skip if sneaking
            return super.use(worldIn, player, hand);
        }

        double reach = Math.min(3.5,player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE));
        Vec3 eye = player.getEyePosition();
        Vec3 look = player.getLookAngle();

        // Trace along the cursor so placement works in any direction
        ClipContext clipContext = new ClipContext(eye, eye.add(look.scale(reach)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player);
        BlockHitResult hit = worldIn.clip(clipContext);

        BlockPos pos;
        if (hit.getType() == HitResult.Type.BLOCK) {
            // Cursor points at a block, so place next to the face being looked at
            pos = hit.getBlockPos().relative(hit.getDirection());
        } else {
            // Cursor points into open air, so place at the end of the reach
            pos = BlockPos.containing(eye.add(look.scale(reach)));
        }
        if (worldIn.isClientSide == false && worldIn.isEmptyBlock(pos)) {
            ItemStack stac = player.getItemInHand(hand);
            if (worldIn.setBlockAndUpdate(pos, Block.byItem(this).defaultBlockState())) {
                if (!player.isCreative()) {
                    stac.shrink(1);
                }
            }
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, stac);
        }
        return super.use(worldIn, player, hand);
    }
}
