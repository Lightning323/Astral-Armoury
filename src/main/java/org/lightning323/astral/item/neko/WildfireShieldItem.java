package org.lightning323.astral.item.neko;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.lightning323.astral.item.shield.AstralShieldItem;

/**
 * Wildfire shield ported from Neko's {@code WILDFIRE_SHIELD}.
 *
 * <p>Neko (1.21.11) configures blocking through the {@code BLOCKS_ATTACKS}
 * component; that component does not exist on 1.21.1, where shields block via
 * hardcoded {@link net.minecraft.world.item.ShieldItem} behaviour — which this
 * item inherits. 336 durability, fire resistant, repaired with netherite.</p>
 */
public class WildfireShieldItem extends AstralShieldItem {
    public WildfireShieldItem(Properties properties) {
        super("entity/shield/wildfire_base", "entity/shield/wildfire_base_nopattern", properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(Items.NETHERITE_INGOT) || super.isValidRepairItem(stack, repairCandidate);
    }
}
