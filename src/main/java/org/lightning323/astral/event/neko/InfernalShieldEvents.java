package org.lightning323.astral.event.neko;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import org.lightning323.astral.Astral;
import org.lightning323.astral.registries.AstralItems;

/**
 * Infernal shield set bonus: an attacker whose hit is blocked by a raised
 * infernal shield is set on fire on contact.
 */
@EventBusSubscriber(modid = Astral.MODID, bus = EventBusSubscriber.Bus.GAME)
public class InfernalShieldEvents {
    private static final int IGNITE_TICKS = 20 * 3;

    @SubscribeEvent
    public static void onShieldBlock(LivingShieldBlockEvent event) {
        if (!(event.getEntity() instanceof Player player) || player.level().isClientSide()) {
            return;
        }
        if (!event.getOriginalBlock()) {
            return;
        }
        if (!player.getMainHandItem().is(AstralItems.INFERNAL_SHIELD.get())
                && !player.getOffhandItem().is(AstralItems.INFERNAL_SHIELD.get())) {
            return;
        }
        if (event.getDamageSource().getEntity() instanceof LivingEntity attacker) {
            attacker.igniteForTicks(IGNITE_TICKS);
        }
    }
}