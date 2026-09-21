package org.lightning323.astral.item.neko;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Anchor heavy weapon ported from Neko's {@code AnchorItem} +
 * {@code ModItemSettings.anchor(12.0F, -3.5F)}.
 *
 * <p>Stats (set on the item properties at registration): 2500 durability,
 * +12 attack damage, -3.5 attack speed, +1.5 entity reach, repaired with
 * prismarine shards.</p>
 */
public class AnchorItem extends Item {
    public AnchorItem(Properties settings) {
        super(settings);
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(Items.PRISMARINE_SHARD);
    }
}
