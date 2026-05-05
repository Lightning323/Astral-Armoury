package org.lightning323.astral.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.lightning323.astral.item.shield.ModShieldDecorationRecipe;
import org.lightning323.astral.registries.AstralItems;
import org.lightning323.astral.registries.AstralRecipes;

import java.util.concurrent.CompletableFuture;

import static org.lightning323.astral.Astral.MODID;

public class ModRecipeProvider extends RecipeProvider {

    // 1.21.1 requires a CompletableFuture for HolderLookup.Provider in the constructor
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        // Smithing Transform Recipe
        // Note: The structure remains largely the same, but we pass 'output' instead of 'writer'
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), // Template
                        Ingredient.of(Items.NETHERITE_SWORD),                      // Base Item
                        Ingredient.of(Items.NETHER_STAR),                         // Addition Material
                        RecipeCategory.COMBAT,
                        AstralItems.LIGHTS_BANE.get()                             // Result
                )
                .unlocks("has_nether_star", has(Items.NETHER_STAR))
                // ResourceLocation.fromNamespaceAndPath is correct for 1.21.1
                .save(output, ResourceLocation.fromNamespaceAndPath(MODID, "lights_bane_smithing"));

        /*
         * Shield decoration recipe
         * Special recipes now use SpecialRecipeBuilder.special(serializer)
         */
        SpecialRecipeBuilder.special(ModShieldDecorationRecipe::new)
                .save(output, ResourceLocation.fromNamespaceAndPath(MODID, "shield_decoration"));

        // Example of a commented-out Shaped Recipe converted to 1.21 syntax:
        /*
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AstralItems.LIGHTS_BANE.get())
            .pattern("I")
            .pattern("I")
            .pattern("S")
            .define('I', Items.CRYING_OBSIDIAN)
            .define('S', Items.NETHER_STAR)
            .unlockedBy("has_iron", has(Items.IRON_INGOT))
            .save(output);
        */
    }
}