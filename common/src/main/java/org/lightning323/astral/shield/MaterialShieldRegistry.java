package org.lightning323.astral.shield;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.lightning323.astral.registries.ModItems;

import static org.lightning323.astral.AstralArmoury.MOD_ID;

public class MaterialShieldRegistry {

    public static final Material SHIELD_BASE_LEATHER = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base"));
    public static final Material SHIELD_BASE_LEATHER_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base_nopattern"));

    public static final Material SHIELD_BASE_OBSIDIAN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base"));
    public static final Material SHIELD_BASE_OBSIDIAN_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base_nopattern"));

    public static final Material SHIELD_BASE_NETHERITE = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/netherite_base"));
    public static final Material SHIELD_BASE_NETHERITE_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/netherite_base_nopattern"));

    public static void init() {
        //this matches up with ShieldCyclicItem where it calls startUsingItem() inside of use()
        ClampedItemPropertyFunction blockFn = (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;

        ItemPropertiesRegistry.register(
                ModItems.SHIELD_LEATHER.get(),
                AstralShieldItem.BLOCKING,
                blockFn
        );

        ItemPropertiesRegistry.register(
                ModItems.SHIELD_OBSIDIAN.get(),
                AstralShieldItem.BLOCKING,
                blockFn
        );

        ItemPropertiesRegistry.register(
                ModItems.SHIELD_NETHERITE.get(),
                AstralShieldItem.BLOCKING,
                blockFn
        );
    }

    public static Material getMaterial(ItemStack stackIn, boolean isBanner) {
        Material rendermaterial = isBanner ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;
        if (stackIn.is(ModItems.SHIELD_LEATHER.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_LEATHER : MaterialShieldRegistry.SHIELD_BASE_LEATHER_NOPATTERN;
        } else if (stackIn.is(ModItems.SHIELD_OBSIDIAN.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_OBSIDIAN : MaterialShieldRegistry.SHIELD_BASE_OBSIDIAN_NOPATTERN;
        } else if (stackIn.is(ModItems.SHIELD_NETHERITE.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_NETHERITE : MaterialShieldRegistry.SHIELD_BASE_NETHERITE_NOPATTERN;
        }
        return rendermaterial;
    }


}
