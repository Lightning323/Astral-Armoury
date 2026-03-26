package org.lightning323.astral.registries;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.blocks.spikes.EnumSpikeType;
import org.lightning323.astral.blocks.spikes.SpikesBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class AstralBlocks {
    public static final Registrar<Block> BLOCKS = AstralArmoury.REGISTRIES.get().get(Registries.BLOCK);

    public static final List<RegistrySupplier<Block>> allBlocks = new ArrayList<>();
    public static final List<RegistrySupplier<Block>> lootDropSelf = new ArrayList<>();

//    public static final RegistryObject<Block> SCAFFOLD_FRAGILE = BLOCKS.register("scaffold_fragile", () -> new BlockScaffolding(Block.Properties.of(), true));
//    public static final RegistryObject<Block> SCAFFOLD_RESPONSIVE = BLOCKS.register("scaffold_responsive", () -> new BlockScaffoldingResponsive(Block.Properties.of(), false));

    public static final RegistrySupplier<Block> SPIKES_IRON = registerBlock("spikes_iron", () -> new SpikesBlock(Block.Properties.of(), EnumSpikeType.PLAIN));
    public static final RegistrySupplier<Block> SPIKES_FIRE = registerBlock("spikes_fire", () -> new SpikesBlock(Block.Properties.of(), EnumSpikeType.FIRE));
    public static final RegistrySupplier<Block> SPIKES_CURSE = registerBlock("spikes_curse", () -> new SpikesBlock(Block.Properties.of(), EnumSpikeType.CURSE));

    private static RegistrySupplier<Block> registerBlock(String resourceLocation, Supplier<Block> supplier) {
        RegistrySupplier<Block> register = BLOCKS.register(AstralArmoury.resource(resourceLocation), supplier);
        lootDropSelf.add(register);
        allBlocks.add(register);
        return register;
    }

    public static void register() {
    }
}
