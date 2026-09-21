package org.lightning323.astral.item.neko;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * Crown smithing template ported from Neko's
 * {@code CROWN_SMITHING_TEMPLATE}. Combines a vanilla helmet with a Heart of
 * the Nether to forge the matching crown.
 *
 * <p>1.21.1 adaptation: plain {@link Item} with template-style tooltips (see
 * {@link NetherHeartItem} for why we don't extend
 * {@code SmithingTemplateItem} here).</p>
 */
public class CrownTemplateItem extends Item {
    public CrownTemplateItem(Properties settings) {
        super(settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> lines, TooltipFlag flag) {
        lines.add(Component.translatable("item.astral.smithing_template.crown.upgrade")
                .withStyle(net.minecraft.ChatFormatting.GRAY));
        lines.add(Component.literal(" "));
        lines.add(Component.translatable("item.astral.smithing_template.crown.applies_to")
                .withStyle(net.minecraft.ChatFormatting.BLUE));
        lines.add(Component.translatable("item.astral.smithing_template.crown.ingredients")
                .withStyle(net.minecraft.ChatFormatting.BLUE));
        super.appendHoverText(stack, context, lines, flag);
    }
}
