package org.lightning323.astral.forge.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.lightning323.astral.registries.AstralBlocks;

import java.util.List;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    protected ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        AstralBlocks.lootDropSelf.forEach(block -> {
            System.out.println(block.get());
            this.dropSelf(block.get());
        });
    }

    //We have to override this to prevent the datagen from iterating over every block in the game
    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> blocks = new java.util.ArrayList<>();
        for (RegistrySupplier<Block> entry : AstralBlocks.allBlocks) {
            blocks.add(entry.get());
        }
        return blocks;
    }

}