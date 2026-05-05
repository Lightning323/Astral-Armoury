package org.lightning323.astral.registries;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.lightning323.astral.CommonClass;

import java.util.EnumMap;
import java.util.List;

import static org.lightning323.astral.AstralArmoury.MOD_ID;

public class AstralArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, MOD_ID);

    public static final RegistryObject<ArmorMaterial> GLOWING_ARMOR = ARMOR_MATERIALS.register("glowing",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 0);
                        map.put(ArmorItem.Type.LEGGINGS, 0);
                        map.put(ArmorItem.Type.CHESTPLATE, 0);
                        map.put(ArmorItem.Type.HELMET, 2); // Iron-tier helmet
                    }),
                    12,
                    SoundEvents.ARMOR_EQUIP_GENERIC,
                    () -> Ingredient.of(Items.GLOWSTONE_DUST),
                    List.of(new ArmorMaterial.Layer(CommonClass.resource( "glowing"))),
                    0.10F,
                    0F
            ));

    public static final RegistryObject<ArmorMaterial> COPPER_ARMOR = ARMOR_MATERIALS.register("copper",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 5);
                        map.put(ArmorItem.Type.CHESTPLATE, 6);
                        map.put(ArmorItem.Type.HELMET, 2);
                    }),
                    12,
                    SoundEvents.ARMOR_EQUIP_GENERIC,
                    () -> Ingredient.of(Items.COPPER_INGOT),
                    List.of(new ArmorMaterial.Layer(CommonClass.resource( "copper"))),
                    0F,
                    0F
            ));

    public static final RegistryObject<ArmorMaterial> CACTUS_ARMOR = ARMOR_MATERIALS.register("cactus",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorMap.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 5);
                        map.put(ArmorItem.Type.CHESTPLATE, 5);
                        map.put(ArmorItem.Type.HELMET, 2);
                    }),
                    6,
                    SoundEvents.ARMOR_EQUIP_GENERIC,
                    () -> Ingredient.of(Items.CACTUS),
                    List.of(new ArmorMaterial.Layer(CommonClass.resource( "cactus"))),
                    0F,
                    0F
            ));

    public static final RegistryObject<ArmorMaterial> EMERALD_ARMOR = ARMOR_MATERIALS.register("emerald",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 5);
                        map.put(ArmorItem.Type.CHESTPLATE, 7);
                        map.put(ArmorItem.Type.HELMET, 2);
                    }),
                    35,
                    AstralSounds.EQUIP_EMERALD.getHolder().get(), // Updated for Holder system
                    () -> Ingredient.of(Items.EMERALD_BLOCK),
                    List.of(new ArmorMaterial.Layer(CommonClass.resource("emerald"))),
                    0.25F,
                    0.2F
            ));
}