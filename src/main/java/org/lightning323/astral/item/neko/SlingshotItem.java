package org.lightning323.astral.item.neko;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.lightning323.astral.entity.neko.SlingshotProjectile;
import org.lightning323.astral.registries.AstralItemTags;

/**
 * Slingshot ranged weapon ported from Neko's {@code SlingshotItem}.
 *
 * <p>1.21.1 adaptations: instead of subclassing the newer
 * {@code ProjectileWeaponItem} pipeline, this is a plain {@link Item} that
 * draws its own ammo (anything in {@code astral:slingshot_projectiles}) and
 * fires a {@link SlingshotProjectile}. Charge, damage and ammo behaviour
 * otherwise mirror Neko. Resin-clump ammo is cut: resin does not exist in
 * 1.21.1.</p>
 */
public class SlingshotItem extends Item {
    public SlingshotItem(Properties settings) {
        super(settings);
    }

    public static float getPullProgress(int useTicks) {
        float progress = useTicks / 20.0F;
        progress = (progress * progress + progress * 2.0F) / 1.5F;
        if (progress > 1.0F) progress = 1.0F;
        return progress;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!player.hasInfiniteMaterials() && findAmmo(player) == null) {
            return InteractionResultHolder.fail(stack);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {
        if (!(user instanceof Player player)) {
            return;
        }
        ItemStack ammo = findAmmo(player);
        if (ammo == null) {
            return;
        }
        float pull = getPullProgress(this.getUseDuration(stack, user) - timeLeft);
        if (pull < 0.99F) {
            return;
        }
        boolean shatter = EnchantLevels.enchantLevel(stack, "multishot") > 0;
        if (level instanceof ServerLevel) {
            ItemStack pellet = ammo.copy();
            pellet.setCount(1);
            SlingshotProjectile projectile = new SlingshotProjectile(level, player, pellet, stack, shatter);
            float speed = pull * 3.0F * (pellet.is(Items.AMETHYST_SHARD) ? (1 / 2F) : 2 / 3F);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, speed, 1.0F);
            level.addFreshEntity(projectile);
            if (!player.hasInfiniteMaterials()) {
                ammo.shrink(1);
            }
            stack.hurtAndBreak(1, player, player.getUsedItemHand() == InteractionHand.MAIN_HAND
                    ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pull * 0.5F);
        player.awardStat(Stats.ITEM_USED.get(this));
    }

    /**
     * Finds usable ammo: held stacks first, then the rest of the inventory.
     * Returns the live stack (so callers can shrink it), or null.
     */
    private static ItemStack findAmmo(Player player) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack held = player.getItemInHand(hand);
            if (held.is(AstralItemTags.SLINGSHOT_PROJECTILES)) {
                return held;
            }
        }
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack candidate = player.getInventory().getItem(i);
            if (candidate.is(AstralItemTags.SLINGSHOT_PROJECTILES)) {
                return candidate;
            }
        }
        return null;
    }
}
