package org.lightning323.astral.shield;

import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ShieldItem;

//Items are singletons
public class AstralShieldItem extends ShieldItem implements Equipable {


    public static final ResourceLocation BLOCKING = new ResourceLocation("minecraft:blocking");
    public final Material base, noPattern;

    public AstralShieldItem(Material base, Material noPattern, Properties properties) {
        super(properties);
        this.base = base;
        this.noPattern = noPattern;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.MAINHAND;
    }
}
