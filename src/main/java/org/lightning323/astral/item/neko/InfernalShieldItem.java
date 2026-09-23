package org.lightning323.astral.item.neko;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.lightning323.astral.item.shield.AstralShieldItem;

/**
 * Infernal shield ported from Neko's {@code WILDFIRE_SHIELD}.
 *
 * <p>Neko (1.21.11) configures blocking through the {@code BLOCKS_ATTACKS}
 * component; that component does not exist on 1.21.1, where shields block via
 * hardcoded {@link net.minecraft.world.item.ShieldItem} behaviour — which this
 * item inherits. 336 durability, fire resistant, repaired with netherite.
 * Contacts with a raised shield ignite the attacker (see
 * {@link org.lightning323.astral.event.neko.InfernalShieldEvents}).</p>
 */
public class InfernalShieldItem extends AstralShieldItem {
    public InfernalShieldItem(Properties properties) {
        super("entity/shield/infernal_base", "entity/shield/infernal_base_nopattern", properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(Items.NETHERITE_INGOT) || super.isValidRepairItem(stack, repairCandidate);
    }
}
