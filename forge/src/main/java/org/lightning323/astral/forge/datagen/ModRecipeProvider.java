package org.lightning323.astral.forge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.lightning323.astral.registries.AstralItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        // Simple 1.20.1 Recipe for your Claymore
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AstralItems.CLAYMORE.get())
            .pattern("I")
            .pattern("I")
            .pattern("S")
            .define('I', Items.IRON_INGOT)
            .define('S', Items.STICK)
            .unlockedBy("has_iron", has(Items.IRON_INGOT))
            .save(writer);
    }
}