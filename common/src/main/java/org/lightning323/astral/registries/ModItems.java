package org.lightning323.astral.registries;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.shield.AstralShieldItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.lightning323.astral.AstralArmoury.MOD_ID;

public class ModItems {
    //https://docs.architectury.dev/api/registry

    public static final Registrar<Item> ITEMS = AstralArmoury.REGISTRIES.get().get(Registries.ITEM);

    //With static elements, they are born in the literal order of the code, which is why these have to come first
    public static final List<RegistrySupplier<Item>> shields = new ArrayList<>();

    /**
     * Broadswords
     */
    public static final RegistrySupplier<Item> CLAYMORE = ITEMS.register(
            new ResourceLocation(MOD_ID, "claymore"),
            () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB))
    );


    public static final RegistrySupplier<Item> EMERALD_BOOTS = register("emerald_boots", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_HELMET = register("emerald_helmet", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.HELMET, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_CHESTPLATE = register("emerald_chestplate", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_LEGGINGS = register("emerald_leggings", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));

    public static final RegistrySupplier<Item> EMERALD_SHOVEL = register("emerald_shovel", () -> new ShovelItem(MyCustomTiers.EMERALD, 1, -2.8f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_HOE = register("emerald_hoe", () -> new HoeItem(MyCustomTiers.EMERALD, -1, -3, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_SWORD = register("emerald_sword", () -> new SwordItem(MyCustomTiers.EMERALD, 5, -2.1f, (new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB))));
    public static final RegistrySupplier<Item> EMERALD_PICKAXE = register("emerald_pickaxe", () -> new PickaxeItem(MyCustomTiers.EMERALD, 1, -2.8f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_AXE = register("emerald_axe", () -> new AxeItem(MyCustomTiers.EMERALD, 6, -3, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));

    //Shields (Basically just regular shields with more durability)
    //A vanilla shield has 336 durability
    private static final int VANILLA_SHIELD_DURABILITY = 336;

    public static final RegistrySupplier<Item> SHIELD_LEATHER = registerShield("shield_leather", () -> new AstralShieldItem(
            new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/netherite_base")),
            new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/netherite_base_nopattern")),
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * 2)).arch$tab(ModCreativeModeTabs.ASTRAL_TAB))
    );

    public static final RegistrySupplier<Item> SHIELD_OBSIDIAN = registerShield("shield_obsidian", () -> new AstralShieldItem(
            new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base")),
            new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base_nopattern")),
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * 2)).arch$tab(ModCreativeModeTabs.ASTRAL_TAB))
    );

    public static final RegistrySupplier<Item> SHIELD_NETHERITE = registerShield("shield_netherite", () -> new AstralShieldItem(
            new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base")),
            new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base_nopattern")),
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * 2)).arch$tab(ModCreativeModeTabs.ASTRAL_TAB))
    );

    public static RegistrySupplier<Item> register(String id, Supplier<Item> itemSupplier) {
        return ITEMS.<Item>register(AstralArmoury.resource(id), itemSupplier);
    }

    private static RegistrySupplier<Item> registerShield(String id, Supplier<Item> shieldSupplier) {
        RegistrySupplier<Item> register = ITEMS.<Item>register(AstralArmoury.resource(id), shieldSupplier);
        shields.add(register);
        return register;
    }

    // 3. The init method called by platform-specific loaders
    public static void register() {

    }
}