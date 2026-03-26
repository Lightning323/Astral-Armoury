package org.lightning323.astral.shield;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;

public class AstralShieldItem extends ShieldItem implements Equipable {

    public static final ResourceLocation BLOCKING = new ResourceLocation("minecraft:blocking");

    public AstralShieldItem(Properties properties, Item repairItem) {
        super(properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.MAINHAND;
    }
}
