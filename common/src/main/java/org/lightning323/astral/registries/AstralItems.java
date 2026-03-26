package org.lightning323.astral.registries;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.GlowHelmet;
import org.lightning323.astral.shield.AstralShieldItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.lightning323.astral.AstralArmoury.MOD_ID;

public class AstralItems {
    //https://docs.architectury.dev/api/registry

    public static final Registrar<Item> ITEMS = AstralArmoury.REGISTRIES.get().get(Registries.ITEM);

    //With static elements, they are born in the literal order of the code, which is why these have to come first
    public static final List<RegistrySupplier<Item>> basicItems = new ArrayList<>();
    public static final List<RegistrySupplier<Item>> shields = new ArrayList<>();
    public static final List<RegistrySupplier<Item>> tools = new ArrayList<>();
    public static final List<RegistrySupplier<Item>> armor = new ArrayList<>();

    /**
     * Items
     */
    public static final RegistrySupplier<Item> SNOWFLAKE = registerBasicItem("snowflake", () -> new Item(new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> GOLD_SNOWFLAKE = registerBasicItem("gold_snowflake", () -> new Item(new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> GEM_PRISMARINE = registerBasicItem("gem_prismarine", () -> new Item(new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> GEM_AMETHYST = registerBasicItem("gem_amethyst", () -> new Item(new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));

    /**
     * Broadswords
     */
    public static final RegistrySupplier<Item> CLAYMORE = registerTool("claymore", null, () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> GLOW_HELMET = registerArmor("glowing_helmet", () -> new GlowHelmet(new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));


    //Amethyst tools
    public static final RegistrySupplier<Item> AMETHYST_SHOVEL = registerTool("amethyst_shovel", () -> new ShovelItem(AstralToolTiers.AMETHYST, 0, -2.9f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> AMETHYST_HOE = registerTool("amethyst_hoe", () -> new HoeItem(AstralToolTiers.AMETHYST, -1, -2.9f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> AMETHYST_SWORD = registerTool("amethyst_sword", () -> new SwordItem(AstralToolTiers.AMETHYST, 2, -2.9f, (new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB))));
    public static final RegistrySupplier<Item> AMETHYST_PICKAXE = registerTool("amethyst_pickaxe", () -> new PickaxeItem(AstralToolTiers.AMETHYST, 1, -2.9f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> AMETHYST_AXE = registerTool("amethyst_axe", () -> new AxeItem(AstralToolTiers.AMETHYST, 4, -2.9f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));

    /**
     * Copper tier (1.20.1 - 1.21.9 only)
     */
    public static final RegistrySupplier<Item> COPPER_SHOVEL = registerTool("copper_shovel", () -> new ShovelItem(AstralToolTiers.COPPER, 1, -2.8f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> COPPER_HOE = registerTool("copper_hoe", () -> new HoeItem(AstralToolTiers.COPPER, -1, -2f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> COPPER_SWORD = registerTool("copper_sword", () -> new SwordItem(AstralToolTiers.COPPER, 3, -2.4f, (new Item.Properties()).arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> COPPER_PICKAXE = registerTool("copper_pickaxe", () -> new PickaxeItem(AstralToolTiers.COPPER, 1, -2.8f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> COPPER_AXE = registerTool("copper_axe", () -> new AxeItem(AstralToolTiers.COPPER, 7, -3.4f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));

    public static final RegistrySupplier<Item> COPPER_BOOTS = registerArmor("copper_boots",null, () -> new ArmorItem(AstralArmorMaterials.COPPER_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> COPPER_HELMET = registerArmor("copper_helmet", () -> new ArmorItem(AstralArmorMaterials.COPPER_ARMOR, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistrySupplier<Item> COPPER_CHESTPLATE = registerArmor("copper_chestplate", () -> new ArmorItem(AstralArmorMaterials.COPPER_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> COPPER_LEGGINGS = registerArmor("copper_leggings", () -> new ArmorItem(AstralArmorMaterials.COPPER_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));

    /**
     * Emerald tier
     */
    public static final RegistrySupplier<Item> EMERALD_BOOTS = registerArmor("emerald_boots", null, () -> new ArmorItem(AstralArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_HELMET = registerArmor("emerald_helmet", null, () -> new ArmorItem(AstralArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.HELMET, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_CHESTPLATE = registerArmor("emerald_chestplate", null, () -> new ArmorItem(AstralArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_LEGGINGS = registerArmor("emerald_leggings", null, () -> new ArmorItem(AstralArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));

    public static final RegistrySupplier<Item> EMERALD_SHOVEL = registerTool("emerald_shovel", null, () -> new ShovelItem(AstralToolTiers.EMERALD, 1, -2.8f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_HOE = registerTool("emerald_hoe", null, () -> new HoeItem(AstralToolTiers.EMERALD, -1, -3, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_SWORD = registerTool("emerald_sword", null, () -> new SwordItem(AstralToolTiers.EMERALD, 5, -2.1f, (new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB))));
    public static final RegistrySupplier<Item> EMERALD_PICKAXE = registerTool("emerald_pickaxe", null, () -> new PickaxeItem(AstralToolTiers.EMERALD, 1, -2.8f, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> EMERALD_AXE = registerTool("emerald_axe", null, () -> new AxeItem(AstralToolTiers.EMERALD, 6, -3, new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));

    /**
     * Blocks
     */
    public static final RegistrySupplier<Item> SPIKES_IRON = register("spikes_iron", () -> new BlockItem(AstralBlocks.SPIKES_IRON.get(), new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> SPIKES_CURSE = register("spikes_curse", () -> new BlockItem(AstralBlocks.SPIKES_CURSE.get(), new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));
    public static final RegistrySupplier<Item> SPIKES_FIRE = register("spikes_fire", () -> new BlockItem(AstralBlocks.SPIKES_FIRE.get(), new Item.Properties().arch$tab(ModCreativeModeTabs.ASTRAL_TAB)));


    //Shields (Basically just regular shields with more durability)
    //A vanilla shield has 336 durability
    private static final int VANILLA_SHIELD_DURABILITY = 336;


    //TODO: we added suppliers for materials because of Figure out net.minecraft.client.renderer.Sheets loaded too early, modded registry-based materials may not work correctly
    public static final RegistrySupplier<Item> SHIELD_LEATHER = registerShield("shield_leather", "Leather Shield", () -> new AstralShieldItem(
            () -> new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/netherite_base")),
            () -> new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/netherite_base_nopattern")),
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * 2)).arch$tab(ModCreativeModeTabs.ASTRAL_TAB))
    );

    public static final RegistrySupplier<Item> SHIELD_OBSIDIAN = registerShield("shield_obsidian", "Obsidian Shield", () -> new AstralShieldItem(
            () -> new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base")),
            () -> new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base_nopattern")),
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * 2)).arch$tab(ModCreativeModeTabs.ASTRAL_TAB))
    );

    public static final RegistrySupplier<Item> SHIELD_NETHERITE = registerShield("shield_netherite", "Netherite Shield", () -> new AstralShieldItem(
            () -> new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base")),
            () -> new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base_nopattern")),
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * 2)).arch$tab(ModCreativeModeTabs.ASTRAL_TAB))
    );

    public static RegistrySupplier<Item> register(String id, Supplier<Item> itemSupplier){
        return register(id, null, itemSupplier);
    }

    public static RegistrySupplier<Item> register(String id, String translation, Supplier<Item> itemSupplier) {
        RegistrySupplier<Item> register = ITEMS.<Item>register(AstralArmoury.resource(id), itemSupplier);
        if (translation == null) {
            String[] s = id.split("_");
            for (int i = 0; i < s.length; i++) {
                if (s[i].length() > 1) {
                    s[i] = s[i].substring(0, 1).toUpperCase() + s[i].substring(1).toLowerCase();
                }else{
                    s[i] = s[i].toUpperCase();
                }
            }
            translation = String.join(" ", s);
        }
        AstralArmoury.itemTranslations.put(register, translation);
        return register;
    }

    public static RegistrySupplier<Item> registerBasicItem(String id, Supplier<Item> itemSupplier){
        return registerBasicItem(id, null, itemSupplier);
    }

    public static RegistrySupplier<Item> registerBasicItem(String id, String translation, Supplier<Item> itemSupplier){
        RegistrySupplier<Item> register = register(id, translation, itemSupplier);
        basicItems.add(register);
        return register;
    }

    public static RegistrySupplier<Item> registerTool(String id, Supplier<Item> itemSupplier){
        return registerTool(id, null, itemSupplier);
    }

    public static RegistrySupplier<Item> registerTool(String id, String translation, Supplier<Item> itemSupplier) {
        RegistrySupplier<Item> register = register(id, translation, itemSupplier);
        tools.add(register);
        return register;
    }

    public static RegistrySupplier<Item> registerArmor(String id, Supplier<Item> itemSupplier){
        return registerArmor(id, null, itemSupplier);
    }

    public static RegistrySupplier<Item> registerArmor(String id, String translation, Supplier<Item> itemSupplier) {
        RegistrySupplier<Item> register = register(id, translation, itemSupplier);
        armor.add(register);
        return register;
    }

    private static RegistrySupplier<Item> registerShield(String id, String translation, Supplier<Item> itemSupplier) {
        RegistrySupplier<Item> register = register(id, translation, itemSupplier);
        shields.add(register);
        return register;
    }

    // 3. The init method called by platform-specific loaders
    public static void register() {

    }
}