package org.lightning323.astral.entity.neko;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.lightning323.astral.item.neko.EnchantLevels;
import org.lightning323.astral.registries.AstralItems;

/**
 * Wildfire trident projectile ported from Neko's {@code WildfireTrident}.
 *
 * <p>Extends vanilla {@link ThrownTrident} (damage, enchantments, pickup and
 * riptide-throw pipeline inherited) with Neko's identity layered on top:
 * flame trail, 3s ignition on hit, loyalty return and the wildfire pickup
 * item. Loyalty return is steered manually (like Neko) so the entity can keep
 * its own {@link EntityType} — which is what lets it render with the unique
 * wildfire texture instead of the vanilla trident's.</p>
 */
public class WildfireTridentEntity extends ThrownTrident {
    private boolean dealtDamage = false;
    private int returnTimer = 0;
    private ItemStack weapon = ItemStack.EMPTY;

    public WildfireTridentEntity(EntityType<WildfireTridentEntity> entityType, Level level) {
        super(entityType, level);
    }

    /** Thrown stack copy: preserves enchantments for damage, loyalty and pickup. */
    public void setWeapon(ItemStack stack) {
        this.weapon = stack.copy();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level() instanceof ServerLevel serverLevel) {
            Vec3 pos = this.position();
            serverLevel.sendParticles(ParticleTypes.FLAME, pos.x, pos.y + 0.2, pos.z, 1, 0.0, 0.0, 0.0, 0.0);
        }

        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        int loyalty = EnchantLevels.enchantLevel(this.weapon, "loyalty");
        Entity owner = this.getOwner();
        if (loyalty > 0 && (this.dealtDamage || this.isNoPhysics()) && owner != null) {
            if (!isOwnerAlive()) {
                if (!this.level().isClientSide() && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem());
                }
                this.discard();
                return;
            }
            this.setNoPhysics(true);
            Vec3 toOwner = owner.getEyePosition().subtract(this.position());
            this.setPosRaw(this.getX(), this.getY() + toOwner.y * 0.015 * loyalty, this.getZ());
            double pull = 0.05 * loyalty;
            this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(toOwner.normalize().scale(pull)));
            if (this.returnTimer == 0) {
                this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
            }
            this.returnTimer++;
        }
    }

    private boolean isOwnerAlive() {
        Entity owner = this.getOwner();
        return owner != null && owner.isAlive() && (!(owner instanceof ServerPlayer) || !owner.isSpectator());
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        this.dealtDamage = true;
        if (hitResult.getEntity() instanceof LivingEntity living) {
            living.igniteForTicks(20 * 3);
        }
    }

    @Override
    public ItemStack getWeaponItem() {
        return this.weapon.isEmpty() ? super.getWeaponItem() : this.weapon;
    }

    @Override
    protected ItemStack getPickupItem() {
        return this.weapon.isEmpty() ? super.getPickupItem() : this.weapon;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(AstralItems.WILDFIRE_TRIDENT.get());
    }

    @Override
    protected float getWaterInertia() {
        return 0.99F;
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }
}
