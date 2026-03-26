package org.lightning323.astral.forge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AstralItems.LIGHTS_BANE.get())
            .pattern("I")
            .pattern("I")
            .pattern("S")
            .define('I', Items.IRON_INGOT)
            .define('S', Items.STICK)
            .unlockedBy("has_iron", has(Items.IRON_INGOT))
            .save(writer);

        SpecialRecipeBuilder.special(AstralRecipes.SHIELD_DECORATION.get())
                .save(writer, ResourceLocation.fromNamespaceAndPath(MOD_ID, "shield_decoration").toString());

//        SpecialRecipeBuilder.special(RecipeSerializer.SHIELD_DECORATION)
//                .save(writer, ResourceLocation.fromNamespaceAndPath(MOD_ID, "shield_decoration").toString());
    }
}