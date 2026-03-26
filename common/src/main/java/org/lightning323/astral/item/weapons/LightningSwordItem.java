package org.lightning323.astral.item.weapons;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class LightningSwordItem extends SwordItem {
    public LightningSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();

        // Only spawn lightning on the server side to prevent "ghost" lighting
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            BlockPos pos = target.blockPosition();
            
            // Create the lightning bolt entity
            EntityType.LIGHTNING_BOLT.spawn(serverLevel, (ItemStack) null, null, pos, 
                    MobSpawnType.TRIGGERED, true, true);
        }

        return super.hurtEnemy(stack, target, attacker);
    }
}