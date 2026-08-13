package org.lightning323.astral.event;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.lightning323.astral.Astral;
import org.lightning323.astral.registries.AstralEnchantments;

import java.util.*;

public class PlayerDeathHandler {

    private static final Map<UUID, List<ItemStack>> SOULBOUND_ITEMS = new HashMap<>();

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        List<ItemStack> soulboundItems = new ArrayList<>();
        event.getDrops().removeIf(drop -> {
            ItemStack stack = drop.getItem();

            if (hasSoulbound(stack, player)) {
                soulboundItems.add(stack.copy());
                return true;
            }

            return false;
        });
        if (!soulboundItems.isEmpty()) {
            SOULBOUND_ITEMS.put(player.getUUID(), soulboundItems);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            return;
        }

        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();

//        Astral.LOG.info("Restoring Soulbound items for {}", newPlayer);
        List<ItemStack> soulboundItems = SOULBOUND_ITEMS.remove(oldPlayer.getUUID());
        if (soulboundItems == null || soulboundItems.isEmpty()) {
//            Astral.LOG.info("No Soulbound items to restore.");
            return;
        }

        for (ItemStack stack : soulboundItems) {
//            Astral.LOG.info("Restoring Soulbound item: {}", stack);
            if (!newPlayer.getInventory().add(stack)) {
                // Inventory is somehow full.
                // Drop the item at the player's location rather than deleting it.
                newPlayer.drop(stack, false);
            }
        }
    }

    private static boolean hasSoulbound(ItemStack stack, Player player) {
        Holder<Enchantment> soulbound =
                player.level().registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .getOrThrow(AstralEnchantments.SOULBOUND);

        return EnchantmentHelper.getItemEnchantmentLevel(soulbound, stack) > 0;
    }
}