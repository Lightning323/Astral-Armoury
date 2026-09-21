package org.lightning323.astral.entity.neko;

import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.lightning323.astral.item.neko.EnchantLevels;
import org.lightning323.astral.registries.AstralEntities;

/**
 * Slingshot pellet ported from Neko's {@code SlingshotProjectile}.
 *
 * <ul>
 *   <li>Iron/gold nuggets deal 4/3 damage, amethyst shards 2
 *       (plus Power levels from the slingshot).</li>
 *   <li>Amethyst shards ricochet off blocks a few times.</li>
 *   <li>Multishot on the slingshot stands in for Neko's custom Shatter
 *       enchantment: the pellet bursts into 5 on impact.</li>
 * </ul>
 *
 * <p>Resin-clump ammo is cut: resin does not exist in 1.21.1.</p>
 */
public class SlingshotProjectile extends ThrowableItemProjectile {
    private boolean shatter = false;
    private int ticksStuck = 0;
    private ItemStack weapon = ItemStack.EMPTY;

    public SlingshotProjectile(Level level, LivingEntity owner, ItemStack stack, ItemStack weapon, boolean shatter) {
        super(AstralEntities.SLINGSHOT_PROJECTILE.get(), owner, level);
        this.setItem(stack);
        this.weapon = weapon.copy();
        this.shatter = shatter;
    }

    public SlingshotProjectile(EntityType<SlingshotProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }

    private ParticleOptions getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL
                : new ItemParticleOption(ParticleTypes.ITEM, itemStack);
    }

    @Override
    public void handleEntityEvent(byte status) {
        if (status == EntityEvent.DEATH) {
            for (int i = 0; i < 8; i++) {
                this.level().addParticle(this.getParticleParameters(), this.getX(), this.getY(), this.getZ(),
                        0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            if (!this.level().noCollision(this, this.getBoundingBox().deflate(1.0E-7))) {
                ticksStuck++;
            } else {
                ticksStuck = 0;
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        DamageSource damageSource = this.damageSources().thrown(this, this.getOwner());
        if (entity.hurt(damageSource, getDamage(this.getItem().getItem()))) {
            if (entity instanceof LivingEntity livingEntity) {
                this.applyKnockback(livingEntity, damageSource);
            }
        }
    }

    protected void applyKnockback(LivingEntity target, DamageSource source) {
        double punch = 0.0;
        if (this.weapon != null && !this.weapon.isEmpty()) {
            punch = EnchantLevels.enchantLevel(this.weapon, "punch");
        }
        if (punch > 0.0) {
            double resistance = Math.max(0.0, 1.0 - target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            Vec3 motion = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(punch * 0.6 * resistance);
            if (motion.lengthSqr() > 0.0) target.push(motion.x, 0.1, motion.z);
        }
    }

    private float getDamage(Item item) {
        int damage;
        if (item == Items.GOLD_NUGGET) damage = 3;
        else if (item == Items.IRON_NUGGET) damage = 4;
        else if (item == Items.AMETHYST_SHARD) damage = 2;
        else damage = 2;
        damage += EnchantLevels.enchantLevel(weapon, "power");
        return damage;
    }

    @Override
    protected void onHit(HitResult hitResult) {
        if (shatter && getOwner() instanceof LivingEntity entity) {
            shatter = false;
            for (int i = 0; i < 5; i++) {
                SlingshotProjectile split = new SlingshotProjectile(this.level(), entity, getItem(), weapon, false);
                if (hitResult instanceof BlockHitResult blockHitResult) {
                    split.setPos(this.getX(), this.getY(), this.getZ());
                    Direction.Axis axis = blockHitResult.getDirection().getAxis();
                    Vec3 face = Vec3.atLowerCornerOf(blockHitResult.getDirection().getNormal());
                    if (Math.signum(this.getDeltaMovement().get(axis)) != Math.signum(face.get(axis))) {
                        Vec3 bounce = new Vec3(face.x == 0 ? 1 : -0.9, face.y == 0 ? 1 : -0.9, face.z == 0 ? 1 : -0.9);
                        Vec3 motion = this.getDeltaMovement().multiply(bounce).scale(0.8)
                                .add(new Vec3(this.getRandom().triangle(0, 1), 0.25, this.getRandom().triangle(0, 1)));
                        split.setDeltaMovement(motion);
                        split.hasImpulse = true;
                    }
                } else if (hitResult instanceof EntityHitResult entityHitResult) {
                    Entity hit = entityHitResult.getEntity();
                    split.setPos(hit.getX(), this.getY(), hit.getZ());
                    Vec3 motion = this.getDeltaMovement().scale(0.8)
                            .add(new Vec3(this.getRandom().triangle(0, 1), 0.25, this.getRandom().triangle(0, 1)));
                    split.setDeltaMovement(motion);
                    split.hasImpulse = true;
                }
                this.level().addFreshEntity(split);
            }
        }
        if (hitResult instanceof BlockHitResult blockHitResult && this.getItem().is(Items.AMETHYST_SHARD)
                && (blockHitResult.getDirection() != Direction.UP || this.getDeltaMovement().y < -0.035)
                && ticksStuck < 5) {
            Direction.Axis axis = blockHitResult.getDirection().getAxis();
            Vec3 face = Vec3.atLowerCornerOf(blockHitResult.getDirection().getNormal());
            if (Math.signum(this.getDeltaMovement().get(axis)) != Math.signum(face.get(axis))) {
                Vec3 bounce = new Vec3(face.x == 0 ? 1 : -1, face.y == 0 ? 1 : -1, face.z == 0 ? 1 : -1).scale(0.9);
                this.setDeltaMovement(this.getDeltaMovement().multiply(bounce));
                this.hasImpulse = true;
                this.playSound(SoundEvents.AMETHYST_BLOCK_FALL, 1, 1);
            } else {
                super.onHit(hitResult);
                if (!this.level().isClientSide()) {
                    this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
                    this.discard();
                }
            }
        } else {
            super.onHit(hitResult);
            if (!this.level().isClientSide()) {
                this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
                this.discard();
            }
        }
    }
}
