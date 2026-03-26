package org.lightning323.astral.registries;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.item.shield.ModShieldDecorationRecipe;

public class AstralRecipes {
    // FIX: Change Registrar type from Item to RecipeSerializer
    public static final Registrar<RecipeSerializer<?>> SERIALIZERS =
            AstralArmoury.REGISTRIES.get().get(Registries.RECIPE_SERIALIZER);

    // FIX: Use the SERIALIZERS registrar instead of RECIPES
    public static final RegistrySupplier<RecipeSerializer<? extends CraftingRecipe>> SHIELD_DECORATION =
            SERIALIZERS.<RecipeSerializer<? extends CraftingRecipe>>register(AstralArmoury.resource("shield_decoration"),
                    () -> new SimpleCraftingRecipeSerializer<>(ModShieldDecorationRecipe::new));

    public static void register() {
    }
}