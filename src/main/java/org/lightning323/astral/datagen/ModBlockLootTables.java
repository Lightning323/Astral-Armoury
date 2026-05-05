package org.lightning323.astral.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.lightning323.astral.registries.AstralBlocks;

import java.util.Collections;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {

    // 1.21 requires passing the HolderLookup.Provider through the constructor
    protected ModBlockLootTables(HolderLookup.Provider registries) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // Assuming lootDropSelf is a Collection of DeferredBlocks or RegistryObjects
        AstralBlocks.lootDropSelf.forEach(block -> {
            this.dropSelf(block.get());
        });
    }

    // NeoForge 1.21 still requires getKnownBlocks to prevent validation errors
    // for blocks from other mods/vanilla.
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return AstralBlocks.allBlocks.stream()
                .map(Holder::value) // In 1.21, Registry entries are often Holders
                .collect(Collectors.toList());
    }
}