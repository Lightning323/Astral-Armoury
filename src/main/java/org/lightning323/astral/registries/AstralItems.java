package org.lightning323.astral.registries;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.lightning323.astral.Astral;
import org.lightning323.astral.item.shield.AstralShieldItem;

import static org.lightning323.astral.Astral.MODID;

public class AstralItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    //With static elements, they are born in the literal order of the code, which is why these have to come first
    public static final List<DeferredItem<Item>> basicItems = new ArrayList<>();
    public static final List<DeferredItem<Item>> shields = new ArrayList<>();
    public static final List<DeferredItem<Item>> tools = new ArrayList<>();
    public static final List<DeferredItem<Item>> armor = new ArrayList<>();
    public static final HashMap<DeferredItem<Item>, String> customTextureLocations = new HashMap<>();

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * Items
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     */
    public static final DeferredItem<Item> SNOWFLAKE = registerBasicItem("snowflake", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_SNOWFLAKE = registerBasicItem("gold_snowflake", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_PRISMARINE = registerBasicItem("gem_prismarine", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_AMETHYST = registerBasicItem("gem_amethyst", () -> new Item(new Item.Properties()));

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * Weapons
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     */
    public static final DeferredItem<Item> LIGHTS_BANE =
            registerBasicTool("lights_bane", "Light's Bane", "swords/lights_bane", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
//    public static final Item> PALLADUM_SWORD = registerBasicTool("palladum_sword", null, "swords/palladium_sword", () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties();
//    public static final Item> DARK_LANCE = registerBasicTool("dark_lance", null, "swords/dark_lance", () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties();

    //Staffs
//    public static final Item> FROST_STAFF = registerBasicTool("frost_staff", null, "swords/frost_staff", () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties();
//    public static final Item> AQUA_STAFF = registerBasicTool("aqua_staff", null, "swords/aqua_staff", () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties();
//    public static final Item> EMERALD_STAFF = registerBasicTool("emerald_staff", null, "swords/emerald_staff", () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties();
//    public static final Item> DIAMOND_STAFF = registerBasicTool("diamond_staff", null, "swords/diamond_staff", () -> new SwordItem(Tiers.IRON, 6, -3.2f, new Item.Properties();
//    public static final Item> LIGHTNING_STAFF = registerBasicTool("lightning_staff", null, "swords/lightning_staff", () -> new LightningStaffItem(new Item.Properties();

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * Armor
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     */
    //Glow helmet
//    public static final Item> GLOW_HELMET = registerArmor("glowing_helmet", () -> new GlowHelmet(new Item.Properties();


    /**
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * Vanilla tiers
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     */
    public static final DeferredItem<Item> AMETHYST_SHOVEL = registerBasicTool("amethyst_shovel", null, "vanilla_tiers/amethyst_shovel", () -> new ShovelItem(AstralToolTiers.AMETHYST, new Item.Properties()));
    public static final DeferredItem<Item> AMETHYST_HOE = registerBasicTool("amethyst_hoe", null, "vanilla_tiers/amethyst_hoe", () -> new HoeItem(AstralToolTiers.AMETHYST, new Item.Properties()));
    public static final DeferredItem<Item> AMETHYST_SWORD = registerBasicTool("amethyst_sword", null, "vanilla_tiers/amethyst_sword", () -> new SwordItem(AstralToolTiers.AMETHYST, new Item.Properties()));
    public static final DeferredItem<Item> AMETHYST_PICKAXE = registerBasicTool("amethyst_pickaxe", null, "vanilla_tiers/amethyst_pickaxe", () -> new PickaxeItem(AstralToolTiers.AMETHYST, new Item.Properties()));
    public static final DeferredItem<Item> AMETHYST_AXE = registerBasicTool("amethyst_axe", null, "vanilla_tiers/amethyst_axe", () -> new AxeItem(AstralToolTiers.AMETHYST, new Item.Properties()));

    /**
     * Cactus tier
     */
    public static final DeferredItem<Item> CACTUS_SWORD = registerBasicTool("cactus_sword", null, "vanilla_tiers/cactus_sword", () -> new SwordItem(AstralToolTiers.CACTUS, new Item.Properties()));

    public static final DeferredItem<Item> CACTUS_BOOTS = registerArmor("cactus_boots", null, "vanilla_tiers/cactus_boots", () -> new ArmorItem(AstralArmorMaterials.CACTUS_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final DeferredItem<Item> CACTUS_HELMET = registerArmor("cactus_helmet", null, "vanilla_tiers/cactus_helmet", () -> new ArmorItem(AstralArmorMaterials.CACTUS_ARMOR, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> CACTUS_CHESTPLATE = registerArmor("cactus_chestplate", null, "vanilla_tiers/cactus_chestplate", () -> new ArmorItem(AstralArmorMaterials.CACTUS_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> CACTUS_LEGGINGS = registerArmor("cactus_leggings", null, "vanilla_tiers/cactus_leggings", () -> new ArmorItem(AstralArmorMaterials.CACTUS_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties()));


    /**
     * Copper tier (1.20.1 - 1.21.9 only)
     */
    public static final DeferredItem<Item> COPPER_SHOVEL = registerBasicTool("copper_shovel", null, "vanilla_tiers/copper_shovel", () -> new ShovelItem(AstralToolTiers.COPPER, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_HOE = registerBasicTool("copper_hoe", null, "vanilla_tiers/copper_hoe", () -> new HoeItem(AstralToolTiers.COPPER, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_SWORD = registerBasicTool("copper_sword", null, "vanilla_tiers/copper_sword", () -> new SwordItem(AstralToolTiers.COPPER, (new Item.Properties())));
    public static final DeferredItem<Item> COPPER_PICKAXE = registerBasicTool("copper_pickaxe", null, "vanilla_tiers/copper_pickaxe", () -> new PickaxeItem(AstralToolTiers.COPPER, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_AXE = registerBasicTool("copper_axe", null, "vanilla_tiers/copper_axe", () -> new AxeItem(AstralToolTiers.COPPER, new Item.Properties()));

    public static final DeferredItem<Item> COPPER_BOOTS = registerArmor("copper_boots", null, "vanilla_tiers/copper_boots", () -> new ArmorItem(AstralArmorMaterials.COPPER_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_HELMET = registerArmor("copper_helmet", null, "vanilla_tiers/copper_helmet", () -> new ArmorItem(AstralArmorMaterials.COPPER_ARMOR, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_CHESTPLATE = registerArmor("copper_chestplate", null, "vanilla_tiers/copper_chestplate", () -> new ArmorItem(AstralArmorMaterials.COPPER_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_LEGGINGS = registerArmor("copper_leggings", null, "vanilla_tiers/copper_leggings", () -> new ArmorItem(AstralArmorMaterials.COPPER_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    /**
     * Emerald tier
     */
    public static final DeferredItem<Item> EMERALD_BOOTS = registerArmor("emerald_boots", null, "vanilla_tiers/emerald_boots", () -> new ArmorItem( AstralArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final DeferredItem<Item> EMERALD_HELMET = registerArmor("emerald_helmet", null, "vanilla_tiers/emerald_helmet", () -> new ArmorItem(AstralArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> EMERALD_CHESTPLATE = registerArmor("emerald_chestplate", null, "vanilla_tiers/emerald_chestplate", () -> new ArmorItem(AstralArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> EMERALD_LEGGINGS = registerArmor("emerald_leggings", null, "vanilla_tiers/emerald_leggings", () -> new ArmorItem(AstralArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final DeferredItem<Item> EMERALD_SHOVEL = registerBasicTool("emerald_shovel", null, "vanilla_tiers/emerald_shovel", () -> new ShovelItem(AstralToolTiers.EMERALD, new Item.Properties()));
    public static final DeferredItem<Item> EMERALD_HOE = registerBasicTool("emerald_hoe", null, "vanilla_tiers/emerald_hoe", () -> new HoeItem(AstralToolTiers.EMERALD, new Item.Properties()));
    public static final DeferredItem<Item> EMERALD_SWORD = registerBasicTool("emerald_sword", null, "vanilla_tiers/emerald_sword", () -> new SwordItem(AstralToolTiers.EMERALD, new Item.Properties()));
    public static final DeferredItem<Item> EMERALD_PICKAXE = registerBasicTool("emerald_pickaxe", null, "vanilla_tiers/emerald_pickaxe", () -> new PickaxeItem(AstralToolTiers.EMERALD, new Item.Properties()));
    public static final DeferredItem<Item> EMERALD_AXE = registerBasicTool("emerald_axe", null, "vanilla_tiers/emerald_axe", () -> new AxeItem(AstralToolTiers.EMERALD, new Item.Properties()));

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * Blocks
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     */
    public static final DeferredItem<Item> SPIKES_IRON = register("spikes_iron", "Iron Spikes", () -> new BlockItem(AstralBlocks.SPIKES_IRON.get(), new Item.Properties()));
    public static final DeferredItem<Item> SPIKES_CURSE = register("spikes_curse", "Cursed Spikes", () -> new BlockItem(AstralBlocks.SPIKES_CURSE.get(), new Item.Properties()));
    public static final DeferredItem<Item> SPIKES_FIRE = register("spikes_fire", "Hot Spikes", () -> new BlockItem(AstralBlocks.SPIKES_FIRE.get(), new Item.Properties()));
    public static final DeferredItem<Item> SCAFFOLD_FRAGILE = register("scaffold_fragile", "Fragile Angel Scaffolding", () -> new BlockItem(AstralBlocks.SCAFFOLD_FRAGILE.get(), new Item.Properties()));
    public static final DeferredItem<Item> SCAFFOLD_RESPONSIVE = register("scaffold_responsive", "Angel Scaffolding", () -> new BlockItem(AstralBlocks.SCAFFOLD_RESPONSIVE.get(), new Item.Properties()));


    /**
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * Shield
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------------------------------
     */
    private static final int VANILLA_SHIELD_DURABILITY = 336;

    //TODO: we added suppliers for materials because of Figure out net.minecraft.client.renderer.Sheets loaded too early, modded registry-based materials may not work correctly
    public static final DeferredItem<Item> SHIELD_LEATHER = registerShield("shield_leather", "Leather Shield", () -> new AstralShieldItem(
            "entity/shield/leather_base",
            "entity/shield/leather_base_nopattern",
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * 2)))
    );

    public static final DeferredItem<Item> SHIELD_OBSIDIAN = registerShield("shield_obsidian", "Obsidian Shield", () -> new AstralShieldItem(
            "entity/shield/obsidian_base",
            "entity/shield/obsidian_base_nopattern",
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * 8)))
    );

    public static final DeferredItem<Item> SHIELD_NETHERITE = registerShield("shield_netherite", "Netherite Shield", () -> new AstralShieldItem(
            "entity/shield/netherite_base",
            "entity/shield/netherite_base_nopattern",
            new Item.Properties())
    );

    public static DeferredItem<Item> register(String id, Supplier<Item> itemSupplier) {
        return register(id, null, itemSupplier);
    }

    public static DeferredItem<Item> register(String id, String translation, Supplier<Item> itemSupplier) {
        DeferredItem<Item> register = ITEMS.register(id, itemSupplier);

        if (translation == null) {
            String[] s = id.split("_");
            for (int i = 0; i < s.length; i++) {
                if (s[i].length() > 1) {
                    s[i] = s[i].substring(0, 1).toUpperCase() + s[i].substring(1).toLowerCase();
                } else {
                    s[i] = s[i].toUpperCase();
                }
            }
            translation = String.join(" ", s);
        }
        Astral.itemTranslations.put(register, translation);
        return register;
    }

    public static DeferredItem<Item> registerBasicItem(String id, Supplier<Item> itemSupplier) {
        return registerBasicItem(id, null, null, itemSupplier);
    }

    public static DeferredItem<Item> registerBasicItem(String id, String translation, String customTextureResource, Supplier<Item> itemSupplier) {
        DeferredItem<Item> register = register(id, translation, itemSupplier);
        basicItems.add(register);
        if (customTextureResource != null) {
            customTextureLocations.put(register, customTextureResource);
        }
        return register;
    }

    public static DeferredItem<Item> registerBasicTool(String id, Supplier<Item> itemSupplier) {
        return registerBasicTool(id, null, null, itemSupplier);
    }

    public static DeferredItem<Item> registerBasicTool(String id, String translation, String customTextureResource, Supplier<Item> itemSupplier) {
        DeferredItem<Item> register = register(id, translation, itemSupplier);
        tools.add(register);
        if (customTextureResource != null) {
            customTextureLocations.put(register, customTextureResource);
        }
        return register;
    }

    public static DeferredItem<Item> registerArmor(String id, Supplier<Item> itemSupplier) {
        return registerArmor(id, null, null, itemSupplier);
    }

    public static DeferredItem<Item> registerArmor(String id, String translation, String customTextureResource, Supplier<Item> itemSupplier) {
        DeferredItem<Item> register = register(id, translation, itemSupplier);
        armor.add(register);
        if (customTextureResource != null) {
            customTextureLocations.put(register, customTextureResource);
        }
        return register;
    }

    private static DeferredItem<Item> registerShield(String id, String translation, Supplier<Item> itemSupplier) {
        DeferredItem<Item> register = register(id, translation, itemSupplier);
        shields.add(register);
        return register;
    }

    // 3. The init method called by platform-specific loaders
    public static void register(IEventBus modEventBus) {
ITEMS.register(modEventBus);
    }
}