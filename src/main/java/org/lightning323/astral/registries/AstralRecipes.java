package org.lightning323.astral.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.lightning323.astral.Astral;
import org.lightning323.astral.item.shield.ModShieldDecorationRecipe;

public class AstralRecipes {
    // Use NeoForge DeferredRegister for 1.21.1
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Astral.MODID);

    // In 1.21.1, CraftingBookCategory is often required in the constructor of special recipes
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ModShieldDecorationRecipe>> SHIELD_DECORATION =
            SERIALIZERS.register("shield_decoration",
                    () -> new SimpleCraftingRecipeSerializer<>(ModShieldDecorationRecipe::new));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}