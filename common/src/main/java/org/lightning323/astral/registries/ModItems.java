package org.lightning323.astral.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import org.lightning323.astral.AstralArmoury;

import static org.lightning323.astral.AstralArmoury.MOD_ID;

public class ModItems {
    //https://docs.architectury.dev/api/registry

    public static final Registrar<Item> ITEMS = AstralArmoury.REGISTRIES.get().get(Registries.ITEM);

    /**
     * Broadswords
     */
    public static final RegistrySupplier<Item> CLAYMORE = ITEMS.register(
            new ResourceLocation(MOD_ID, "claymore"),
            () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB))
    );

//
//    public static final RegistrySupplier<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties()));
//    public static final RegistrySupplier<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.HELMET, new Item.Properties()));
//    public static final RegistrySupplier<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//    public static final RegistrySupplier<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//
//    public static final RegistrySupplier<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel", () -> new ShovelItem(ModToolMaterials.EMERALD, 1, -2.8f, new Item.Properties()));
//    public static final RegistrySupplier<Item> EMERALD_HOE = ITEMS.register("emerald_hoe", () -> new HoeItem(ModToolMaterials.EMERALD, -1, -3, new Item.Properties()));
//    public static final RegistrySupplier<Item> EMERALD_SWORD = ITEMS.register("emerald_sword", () -> new SwordItem(ModToolMaterials.EMERALD, 5, -2.1f, (new Item.Properties())));
//    public static final RegistrySupplier<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe", () -> new PickaxeItem(ModToolMaterials.EMERALD, 1, -2.8f, new Item.Properties()));
//    public static final RegistrySupplier<Item> EMERALD_AXE = ITEMS.register("emerald_axe", () -> new AxeItem(ModToolMaterials.EMERALD, 6, -3, new Item.Properties()));


    // 3. The init method called by platform-specific loaders
    public static void register() {

    }
}