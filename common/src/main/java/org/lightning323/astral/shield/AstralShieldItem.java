package org.lightning323.astral.shield;

import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ShieldItem;

import java.util.function.Supplier;

//Items are singletons
public class AstralShieldItem extends ShieldItem implements Equipable {

    public static final ResourceLocation BLOCKING = new ResourceLocation("minecraft:blocking");
//    private final Supplier<Material> baseSupplier, noPatternSupplier;
    private Material base, noPattern;

    public Material getBaseMaterial(){
//        if(base == null){
//            base = baseSupplier.get();
//        }
        return base;
    }

    public Material getNoPatternMaterial(){
//        if(noPattern == null){
//            noPattern = noPatternSupplier.get();
//        }
        return noPattern;
    }

    public AstralShieldItem(Supplier<Material> base, Supplier<Material> noPattern, Properties properties) {
        super(properties);
        this.base = base.get();
        this.noPattern = noPattern.get();
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.MAINHAND;
    }
}
