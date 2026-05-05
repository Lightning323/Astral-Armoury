package org.lightning323.astral.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.lightning323.astral.Astral;
import org.lightning323.astral.blocks.angelScaffolding.BlockScaffolding;
import org.lightning323.astral.blocks.angelScaffolding.BlockScaffoldingResponsive;
import org.lightning323.astral.blocks.spikes.EnumSpikeType;
import org.lightning323.astral.blocks.spikes.SpikesBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.lightning323.astral.Astral.MODID;

public class AstralBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final List<DeferredBlock<Block>> allBlocks = new ArrayList<>();
    public static final List<DeferredBlock<Block>> lootDropSelf = new ArrayList<>();

    public static final DeferredBlock<Block> SCAFFOLD_FRAGILE = registerBlock("scaffold_fragile", () -> new BlockScaffolding( true));
    public static final DeferredBlock<Block> SCAFFOLD_RESPONSIVE = registerBlock("scaffold_responsive", () -> new BlockScaffoldingResponsive());

    public static final DeferredBlock<Block> SPIKES_IRON = registerBlock("spikes_iron", () -> new SpikesBlock(Block.Properties.of(), EnumSpikeType.PLAIN));
    public static final DeferredBlock<Block> SPIKES_FIRE = registerBlock("spikes_fire", () -> new SpikesBlock(Block.Properties.of(), EnumSpikeType.FIRE));
    public static final DeferredBlock<Block> SPIKES_CURSE = registerBlock("spikes_curse", () -> new SpikesBlock(Block.Properties.of(), EnumSpikeType.CURSE));

    private static DeferredBlock<Block> registerBlock(String resourceLocation, Supplier<Block> supplier) {
        DeferredBlock<Block> register = BLOCKS.register(resourceLocation, supplier);
        lootDropSelf.add(register);
        allBlocks.add(register);
        return register;
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
