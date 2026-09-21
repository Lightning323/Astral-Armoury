package org.lightning323.astral.entity.neko;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.lightning323.astral.registries.AstralItems;

/**
 * Training dummy ported from Neko's {@code TargetDummy} + {@code TargetDummyItem}.
 *
 * <p>Neko's dummy subclasses the newer {@code Avatar} class (unavailable on
 * 1.21.1), so this port subclasses vanilla {@link ArmorStand}: it shows the
 * last hit's damage above its head, ignores knockback/pushes, never starves a
 * combo, and drops its item when broken by a creative player or the void.</p>
 */
public class TargetDummyEntity extends ArmorStand {
    private int clearNameTicks = 0;

    public TargetDummyEntity(EntityType<TargetDummyEntity> entityType, Level level) {
        super(entityType, level);
    }

    public static ItemStack pickupStack() {
        return new ItemStack(AstralItems.TARGET_DUMMY.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide() && clearNameTicks > 0) {
            clearNameTicks--;
            if (clearNameTicks == 0 && !this.hasCustomName()) {
                this.setCustomNameVisible(false);
            }
            if (clearNameTicks == 0) {
                this.setCustomName(null);
                this.setCustomNameVisible(false);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        // State changes stay server-side so the client never spawns ghost drops.
        if (!this.level().isClientSide()) {
            // Show the dealt damage, like a training dummy should.
            this.setCustomName(Component.literal(String.format("%.1f", amount)));
            this.setCustomNameVisible(true);
            this.clearNameTicks = 40;
            this.level().broadcastEntityEvent(this, (byte) 32);
            this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
            this.playSound(SoundEvents.ARMOR_STAND_HIT, 1.0F, 1.0F);

            // Creative players break it back into an item; survival hits never destroy it.
            if (source.getEntity() instanceof net.minecraft.world.entity.player.Player player
                    && player.getAbilities().instabuild) {
                this.spawnAtLocation(pickupStack());
                this.discard();
            }
        }
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(net.minecraft.world.entity.Entity entity) {
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        // Immune to fire, drowning, etc. so the dummy survives the range.
        return source.is(net.minecraft.tags.DamageTypeTags.IS_FIRE)
                || source.is(net.minecraft.tags.DamageTypeTags.IS_DROWNING)
                || source.is(net.minecraft.tags.DamageTypeTags.IS_FALL)
                || super.isInvulnerableTo(source);
    }
}
