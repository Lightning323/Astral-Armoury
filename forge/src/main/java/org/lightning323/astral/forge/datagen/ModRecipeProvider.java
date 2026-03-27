package org.lightning323.astral.forge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.lightning323.astral.registries.AstralItems;
import org.lightning323.astral.registries.AstralRecipes;

import java.util.function.Consumer;

import static org.lightning323.astral.AstralArmoury.MOD_ID;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        // Simple 1.20.1 Recipe for your Claymore
//        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AstralItems.LIGHTS_BANE.get())
//            .pattern("I")
//            .pattern("I")
//            .pattern("S")
//            .define('I', Items.CRYING_OBSIDIAN)
//            .define('S', Items.NETHER_STAR)
//            .unlockedBy("has_iron", has(Items.IRON_INGOT))
//            .save(writer);

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), // Template
                        Ingredient.of(Items.NETHERITE_SWORD),                          // Base Item
                        Ingredient.of(Items.NETHER_STAR),            // Addition Material
                        RecipeCategory.COMBAT,
                        AstralItems.LIGHTS_BANE.get()                            // Result
                )
                .unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(writer, ResourceLocation.fromNamespaceAndPath(MOD_ID, "lights_bane_smithing"));


        /**
         * Shield decoration recipe (VERY IMPORTANT)
         */
        SpecialRecipeBuilder.special(AstralRecipes.SHIELD_DECORATION.get())
                .save(writer, ResourceLocation.fromNamespaceAndPath(MOD_ID, "shield_decoration").toString());

//        SpecialRecipeBuilder.special(RecipeSerializer.SHIELD_DECORATION)
//                .save(writer, ResourceLocation.fromNamespaceAndPath(MOD_ID, "shield_decoration").toString());
    }
}