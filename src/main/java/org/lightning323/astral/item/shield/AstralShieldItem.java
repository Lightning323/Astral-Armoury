package org.lightning323.astral.item.shield;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ShieldItem;
import org.lightning323.astral.AstralArmoury;

import java.util.function.Supplier;

//Items are singletons
public class AstralShieldItem extends ShieldItem implements Equipable {

    public static final ResourceLocation BLOCKING = new ResourceLocation("minecraft:blocking");
    private final String baseMaterialPath, noPatternMaterialPath;
    private Material base, noPattern;

    public Material getBaseMaterial() {
        //We can only make materials on client side and we shoudnt do this during datagen
        if (base == null) {
            base = new Material(Sheets.SHIELD_SHEET, AstralArmoury.resource(baseMaterialPath));
        }
        return base;
    }

    public Material getNoPatternMaterial() {
        if (noPattern == null) {
            noPattern = new Material(Sheets.SHIELD_SHEET, AstralArmoury.resource(noPatternMaterialPath));
        }
        return noPattern;
    }

    public AstralShieldItem(String baseMaterialPath, String noPatternMaterialPath, Properties properties) {
        super(properties);
        this.baseMaterialPath = baseMaterialPath;
        this.noPatternMaterialPath = noPatternMaterialPath;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.MAINHAND;
    }
}
