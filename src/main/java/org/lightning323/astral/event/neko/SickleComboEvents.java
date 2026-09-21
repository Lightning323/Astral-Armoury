package org.lightning323.astral.event.neko;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.lightning323.astral.Astral;
import org.lightning323.astral.item.neko.SickleItem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Sickle combo system ported from Neko's {@code PlayerMixin} + combo component.
 *
 * <p>Consecutive sickle hits within the combo window deal escalating bonus
 * damage ({@code base * comboSeconds * multiplier%}); taking any damage resets
 * the combo. Per-sickle multipliers mirror Neko's {@code COMBO_MULTIPLIER}
 * component values and are stored on {@link SickleItem} instead of a custom
 * data component (which would need a 1.21.11 component registry).</p>
 */
@EventBusSubscriber(modid = Astral.MODID, bus = EventBusSubscriber.Bus.GAME)
public class SickleComboEvents {
    private static final Map<UUID, Integer> COMBOS = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        UUID id = player.getUUID();
        Integer timer = COMBOS.get(id);
        if (timer != null) {
            if (timer <= 1) {
                COMBOS.remove(id);
            } else {
                COMBOS.put(id, timer - 1);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        // Taking damage resets your own combo (ported from Neko's cancelCombo).
        if (event.getEntity() instanceof Player victim) {
            COMBOS.remove(victim.getUUID());
        }
        if (!(event.getSource().getDirectEntity() instanceof Player attacker)) {
            return;
        }
        if (!(attacker.getMainHandItem().getItem() instanceof SickleItem sickle)) {
            return;
        }
        int timer = COMBOS.getOrDefault(attacker.getUUID(), 0);
        int comboSeconds = ceilDiv(timer, 30);
        float bonus = event.getOriginalDamage() * comboSeconds * sickle.getComboMultiplier() * 0.01F;
        event.setNewDamage(event.getNewDamage() + bonus);
        if (!attacker.level().isClientSide()) {
            COMBOS.put(attacker.getUUID(), Math.min((comboSeconds + 1) * 30, 10 * 30));
        }
    }

    private static int ceilDiv(int x, int y) {
        int quotient = x / y;
        if ((x ^ y) >= 0 && (quotient * y != x)) {
            return quotient + 1;
        }
        return quotient;
    }
}
