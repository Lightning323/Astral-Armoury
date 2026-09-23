package org.lightning323.astral.item.neko;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.lightning323.astral.client.MoltenTridentItemRenderer;
import org.lightning323.astral.entity.neko.MoltenTridentEntity;
import org.lightning323.astral.registries.AstralEntities;

/**
 * Molten trident ported from Neko's {@code WildfireTridentItem}.
 *
 * <p>1.21.1 adaptations (Neko targets the newer spin-attack API):</p>
 * <ul>
 *   <li>Thrown entity is {@link MoltenTridentEntity} (a vanilla-pipeline
 *       {@code ThrownTrident} with flame trail + ignition).</li>
 *   <li>Riptide uses a look-vector dash (the auto spin attack needs newer
 *       enchantment effect hooks).</li>
 *   <li>Loyalty is honoured through the vanilla trident pipeline.</li>
 * </ul>
 */
public class MoltenTridentItem extends Item implements ProjectileItem {
    public MoltenTridentItem(Properties settings) {
        super(settings.rarity(Rarity.RARE).fireResistant().durability(1000));
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }

    @Override
    public void initializeClient(java.util.function.Consumer<net.neoforged.neoforge.client.extensions.common.IClientItemExtensions> consumer) {
        consumer.accept(new net.neoforged.neoforge.client.extensions.common.IClientItemExtensions() {
            private MoltenTridentItemRenderer renderer;

            @Override
            public net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    renderer = new MoltenTridentItemRenderer();
                }
                return renderer;
            }
        });
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        // 1.21.1 has no TRIDENT pose yet; tridents use the spear pose.
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (isTooDamaged(stack)) {
            return InteractionResultHolder.fail(stack);
        }
        int riptide = EnchantLevels.enchantLevel(stack, "riptide");
        if (riptide > 0 && !player.isInWaterOrRain()) {
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
        int charge = this.getUseDuration(stack, user) - timeLeft;
        if (charge < 10) {
            return;
        }
        if (isTooDamaged(stack)) {
            return;
        }
        int riptide = EnchantLevels.enchantLevel(stack, "riptide");
        if (riptide > 0) {
            if (!player.isInWaterOrRain()) {
                return;
            }
            if (level instanceof ServerLevel) {
                Vec3 look = player.getLookAngle();
                float strength = 1.5F * riptide;
                player.push(look.x * strength, look.y * strength + 0.35F, look.z * strength);
                player.hurtMarked = true;
                stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            }
            Holder<SoundEvent> riptideSound = switch (Math.min(riptide, 3)) {
                case 1 -> SoundEvents.TRIDENT_RIPTIDE_1;
                case 2 -> SoundEvents.TRIDENT_RIPTIDE_2;
                default -> SoundEvents.TRIDENT_RIPTIDE_3;
            };
            level.playSound(null, player, riptideSound.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
            player.awardStat(Stats.ITEM_USED.get(this));
            return;
        }
        if (level instanceof ServerLevel) {
            ItemStack thrown = stack.copy();
            thrown.setCount(1);
            if (!player.hasInfiniteMaterials()) {
                stack.shrink(1);
            }
            MoltenTridentEntity trident =
                    new MoltenTridentEntity(AstralEntities.MOLTEN_TRIDENT.get(), level);
            trident.setOwner(player);
            trident.setPos(player.getX(), player.getEyeY() - 0.1D, player.getZ());
            trident.setWeapon(thrown);
            trident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
            if (player.hasInfiniteMaterials()) {
                trident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }
            level.addFreshEntity(trident);
            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        }
        level.playSound(null, player, SoundEvents.TRIDENT_THROW.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
        player.awardStat(Stats.ITEM_USED.get(this));
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        ItemStack thrown = stack.copy();
        thrown.setCount(1);
        MoltenTridentEntity trident =
                new MoltenTridentEntity(AstralEntities.MOLTEN_TRIDENT.get(), level);
        trident.setPos(pos.x(), pos.y(), pos.z());
        trident.setWeapon(thrown);
        trident.pickup = AbstractArrow.Pickup.ALLOWED;
        return trident;
    }

    private static boolean isTooDamaged(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage() - 1;
    }
}
