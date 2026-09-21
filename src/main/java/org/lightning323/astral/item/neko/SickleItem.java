package org.lightning323.astral.item.neko;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.lightning323.astral.registries.AstralItemTags;

import static net.minecraft.world.item.Item.BASE_ATTACK_DAMAGE_ID;
import static net.minecraft.world.item.Item.BASE_ATTACK_SPEED_ID;

/**
 * Sickle weapon ported from Neko's {@code SickleItem}.
 *
 * <p>Stats mirror Neko's {@code ModItemSettings.sickle}: per-tier damage with
 * {@code -2.4} attack speed. Dual-wielding two sickles lets the off-hand sickle
 * strike via use / entity-interact (hand-swap attack). Consecutive hits build a
 * combo multiplier handled by {@code SickleComboEvents}.</p>
 *
 * <p>1.21.1 adaptations: the newer {@code use} signature is replaced with the
 * 1.21.1 holder form, cooldowns take the {@link Item} (not the stack), and the
 * attack-strength-ticker tweak from Neko's mixin-era code is dropped (the
 * field is protected on this version; the 12-tick item cooldown preserves the
 * pacing).</p>
 *
 * <p>Note: plain {@link Item} (not {@link net.minecraft.world.item.SwordItem}) is
 * used to keep Neko's exact damage values. On 1.21.1 this means sword-only
 * enchantments (Sharpness/Smite/...) cannot be applied; Mending/Unbreaking work.</p>
 */
public class SickleItem extends Item {
    /** Matches Neko's {@code SickleItem.SPEED}. */
    public static final float SPEED = -2.4F;

    /** Combo multiplier per sickle (ported from Neko's {@code COMBO_MULTIPLIER} component). */
    private final int comboMultiplier;
    private final Tier tier;

    public SickleItem(Tier tier, float damage, int comboMultiplier, Properties settings) {
        super(settings.component(DataComponents.ATTRIBUTE_MODIFIERS, createAttributes(damage, SPEED)));
        this.tier = tier;
        this.comboMultiplier = comboMultiplier;
    }

    private static ItemAttributeModifiers createAttributes(float damage, float speed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, damage, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .build();
    }

    public int getComboMultiplier() {
        return comboMultiplier;
    }

    @Override
    public int getEnchantmentValue() {
        return tier.getEnchantmentValue();
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return tier.getRepairIngredient().test(repairCandidate);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand hand) {
        ItemStack stack = user.getItemInHand(hand);
        if (hand == InteractionHand.MAIN_HAND) return InteractionResultHolder.pass(stack);
        if (!user.getItemInHand(InteractionHand.MAIN_HAND).is(AstralItemTags.SICKLES)) {
            return InteractionResultHolder.pass(stack);
        }
        if (user.getAttackStrengthScale(0) < 0.5) return InteractionResultHolder.pass(stack);
        user.getCooldowns().addCooldown(stack.getItem(), 12);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity,
                                                  InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        if (!user.getItemInHand(InteractionHand.MAIN_HAND).is(AstralItemTags.SICKLES)) {
            return InteractionResult.PASS;
        }
        if (user.getAttackStrengthScale(0) < 0.5) return InteractionResult.PASS;
        if (user.getCooldowns().getCooldownPercent(stack.getItem(), 0) > 0) return InteractionResult.PASS;
        user.getCooldowns().addCooldown(stack.getItem(), 12);
        if (user.level().isClientSide()) return InteractionResult.SUCCESS;

        swapHands(user);
        user.attack(entity);
        swapHands(user);
        return InteractionResult.SUCCESS;
    }

    private static void swapHands(Player user) {
        ItemStack offHand = user.getItemInHand(InteractionHand.OFF_HAND);
        user.setItemInHand(InteractionHand.OFF_HAND, user.getItemInHand(InteractionHand.MAIN_HAND));
        user.setItemInHand(InteractionHand.MAIN_HAND, offHand);
    }
}
