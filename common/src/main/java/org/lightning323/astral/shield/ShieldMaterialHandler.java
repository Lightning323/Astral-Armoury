package org.lightning323.astral.shield;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.Item;
import org.lightning323.astral.registries.AstralItems;

@Environment(EnvType.CLIENT)
public class ShieldMaterialHandler {

    public static void init() {
        //this matches up with ShieldCyclicItem where it calls startUsingItem() inside of use()
        ClampedItemPropertyFunction blockFn = (stack, world, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;

        for (RegistrySupplier<Item> shield : AstralItems.shields) {
            ItemPropertiesRegistry.register(
                    shield.get(), AstralShieldItem.BLOCKING, blockFn
            );
        }
    }

    public static Material getMaterial(AstralShieldItem item, boolean isBanner) {
//        Material rendermaterial = isBanner ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD; //Default values
        return isBanner ? item.getBaseMaterial() : item.getNoPatternMaterial();
    }
}
