//package org.lightning323.astral.forge.datagen;
//
//import net.minecraft.data.loot.BlockLootSubProvider;
//import net.minecraft.world.flag.FeatureFlags;
//import net.minecraft.world.level.block.Block;
//import org.lightning323.astral.AstralArmoury;
//
//import java.util.Set;
//
//public class ModBlockLootSubProvider extends BlockLootSubProvider {
//    protected ModBlockLootSubProvider() {
//        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
//    }
//
//    @Override
//    protected void generate() {
//        // If you had a "Claymore Forge" block:
////         this.dropSelf(ModBlocks.CLAYMORE_FORGE.get());
//    }
//
//    @Override
//    protected Iterable<Block> getKnownBlocks() {
////        return AstralArmoury.BLOCKS.getRegistrar().getEntries();
//    }
//}