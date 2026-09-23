package org.lightning323.astral.item.neko;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * Heart of the Nether ported from Neko's {@code NETHER_HEART}.
 *
 * <p>1.21.1 adaptation: Neko extends {@code SmithingTemplateItem}, whose
 * 1.21.1 constructor takes no {@code Properties} (no rarity / fire
 * resistance). A plain {@link Item} with template-style tooltips keeps the
 * rarity, fire resistance and full smithing-table functionality on this
 * version.</p>
 *
 * <p>Neko drops it from the Wildfire boss (not ported: mobs are out of
 * scope); in Astral it is the smithing-template ingredient that upgrades
 * tridents and shields into their molten and infernal variants.</p>
 */
public class NetherHeartItem extends Item {
    public NetherHeartItem(Properties settings) {
        super(settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> lines, TooltipFlag flag) {
        lines.add(Component.translatable("item.astral.smithing_template.infernal_upgrade.upgrade")
                .withStyle(net.minecraft.ChatFormatting.GRAY));
        lines.add(Component.literal(" "));
        lines.add(Component.translatable("item.astral.smithing_template.infernal_upgrade.applies_to")
                .withStyle(net.minecraft.ChatFormatting.BLUE));
        lines.add(Component.translatable("item.astral.smithing_template.infernal_upgrade.ingredients")
                .withStyle(net.minecraft.ChatFormatting.BLUE));
        super.appendHoverText(stack, context, lines, flag);
    }
}
