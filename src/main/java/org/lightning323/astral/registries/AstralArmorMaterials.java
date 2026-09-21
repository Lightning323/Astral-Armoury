package org.lightning323.astral.registries;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static org.lightning323.astral.Astral.MODID;

public class AstralArmorMaterials {

    public static final Holder<ArmorMaterial> GLOWING_ARMOR = register("glowing",
            createMap(0, 0, 0, 2),
            12, 0.10F, 0F,
            () -> Items.GLOWSTONE_DUST,
            SoundEvents.ARMOR_EQUIP_GENERIC
    );

    public static final Holder<ArmorMaterial> COPPER_ARMOR = register("copper",
            createMap(2, 5, 6, 2),
            12, 0F, 0F,
            () -> Items.COPPER_INGOT,
            SoundEvents.ARMOR_EQUIP_GENERIC
    );

    public static final Holder<ArmorMaterial> CACTUS_ARMOR = register("cactus",
            createMap(2, 5, 5, 2),
            6, 0F, 0F,
            () -> Items.CACTUS,
            SoundEvents.ARMOR_EQUIP_GENERIC
    );

    public static final Holder<ArmorMaterial> EMERALD_ARMOR = register("emerald",
            createMap(2, 5, 7, 2),
            35, 0.25F, 0.2F,
            () -> Items.EMERALD_BLOCK,
            SoundEvents.ARMOR_EQUIP_GENERIC
    );

    /**
     * Turtle armor (ported from Neko's Turtle Scute set: chestplate, leggings, boots
     * completing the vanilla turtle helmet). Stats mirror iron with turtle enchantability.
     */
    public static final Holder<ArmorMaterial> TURTLE_ARMOR = register("turtle",
            createMap(2, 5, 6, 2),
            9, 0F, 0F,
            () -> Items.TURTLE_SCUTE,
            SoundEvents.ARMOR_EQUIP_TURTLE
    );

    /**
     * Crown helmet materials (ported from Neko's crowns). Each matches its base
     * metal's protection/enchantability so crowns are strict helmet alternatives.
     */
    public static final Holder<ArmorMaterial> COPPER_CROWN_ARMOR = register("copper_crown",
            createMap(2, 5, 6, 2),
            12, 0F, 0F,
            () -> Items.COPPER_INGOT,
            SoundEvents.ARMOR_EQUIP_GENERIC
    );

    public static final Holder<ArmorMaterial> IRON_CROWN_ARMOR = register("iron_crown",
            createMap(2, 5, 6, 2),
            9, 0F, 0F,
            () -> Items.IRON_INGOT,
            SoundEvents.ARMOR_EQUIP_GENERIC
    );

    public static final Holder<ArmorMaterial> GOLDEN_CROWN_ARMOR = register("golden_crown",
            createMap(1, 3, 5, 2),
            25, 0F, 0F,
            () -> Items.GOLD_INGOT,
            SoundEvents.ARMOR_EQUIP_GENERIC
    );

    public static final Holder<ArmorMaterial> DIAMOND_CROWN_ARMOR = register("diamond_crown",
            createMap(3, 6, 8, 3),
            10, 2.0F, 0F,
            () -> Items.DIAMOND,
            SoundEvents.ARMOR_EQUIP_GENERIC
    );

    public static final Holder<ArmorMaterial> NETHERITE_CROWN_ARMOR = register("netherite_crown",
            createMap(3, 6, 8, 3),
            15, 3.0F, 0.1F,
            () -> Items.NETHERITE_INGOT,
            SoundEvents.ARMOR_EQUIP_GENERIC
    );

    // 🔹 Helper: clean stat map
    private static EnumMap<ArmorItem.Type, Integer> createMap(int boots, int leggings, int chestplate, int helmet) {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, boots);
            map.put(ArmorItem.Type.LEGGINGS, leggings);
            map.put(ArmorItem.Type.CHESTPLATE, chestplate);
            map.put(ArmorItem.Type.HELMET, helmet);
        });
    }

    // 🔹 Core register method (matches your example style)
    private static Holder<ArmorMaterial> register(String name,
                                                  EnumMap<ArmorItem.Type, Integer> protection,
                                                  int enchantability,
                                                  float toughness,
                                                  float knockbackResistance,
                                                  Supplier<net.minecraft.world.item.Item> ingredientItem,
                                                  Holder<SoundEvent> equipSound) {

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MODID, name);

        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(id));

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                id,
                new ArmorMaterial(protection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance)
        );
    }
}