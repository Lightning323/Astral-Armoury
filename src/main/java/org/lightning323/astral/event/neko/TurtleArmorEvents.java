package org.lightning323.astral.event.neko;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.lightning323.astral.Astral;
import org.lightning323.astral.registries.AstralItems;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

/**
 * Turtle-armor set bonuses ported from Neko's {@code PlayerMixin}.
 *
 * <ul>
 *   <li>Turtle flippers (boots): Dolphin's Grace while on land. The effect is
 *       refreshed only every 5 seconds (100 ticks) instead of every tick to
 *       cut per-player tick work; the 10-second effect duration still keeps
 *       the buff applied continuously.</li>
 *   <li>Turtle knee pads (leggings): no underwater mining penalty while
 *       submerged.</li>
 *   <li>Vanilla turtle helmet: blocks mace smash attacks at the cost of helmet
 *       durability. 1.21.1 has no {@code MACE_SMASH} damage type yet, so a
 *       smash is approximated as a mace hit from a falling attacker. (Neko
 *       damaged the chest slot here — that looks like a bug, so Astral damages
 *       the helmet instead.)</li>
 * </ul>
 */
@EventBusSubscriber(modid = Astral.MODID, bus = EventBusSubscriber.Bus.GAME)
public class TurtleArmorEvents {
    private static final int BOOST_REFRESH_INTERVAL = 20 * 5;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof Player player) || player.level().isClientSide()) {
            return;
        }
        if (player.tickCount % BOOST_REFRESH_INTERVAL != 0) {
            return;
        }
        if (player.onGround() && !player.isInWater()
                && player.getItemBySlot(EquipmentSlot.FEET).is(AstralItems.TURTLE_BOOTS.get())) {
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 200, 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player.isEyeInFluid(FluidTags.WATER)
                && player.getItemBySlot(EquipmentSlot.LEGS).is(AstralItems.TURTLE_LEGGINGS.get())) {
            // Neko treats the player as on-ground: cancel the 5x water penalty.
            event.setNewSpeed(event.getNewSpeed() * 5.0F);
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!helmet.is(Items.TURTLE_HELMET)) {
            return;
        }
        if (event.getSource().getDirectEntity() instanceof Player attacker
                && attacker.getMainHandItem().is(Items.MACE)
                && attacker.fallDistance > 1.5F) {
            helmet.hurtAndBreak((int) event.getNewDamage(), player, EquipmentSlot.HEAD);
            event.setNewDamage(0.00123F);
        }
    }
}
