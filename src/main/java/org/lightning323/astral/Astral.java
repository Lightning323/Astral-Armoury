package org.lightning323.astral;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.commons.logging.impl.WeakHashtable;
import org.lightning323.astral.registries.*;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Supplier;

import static org.lightning323.astral.registries.AstralItems.ITEMS;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Astral.MODID)
public class Astral {
    public static final String MODID = "astral";
    public static final Logger LOG = LogUtils.getLogger();

    public static HashMap<Supplier<Item>, String> itemTranslations = new HashMap<>();
    public static HashMap<Supplier<Item>, String> translations = new HashMap<>();

    public Astral(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        AstralItems.register(modEventBus);
        AstralBlocks.register(modEventBus);
        AstralRecipes.register(modEventBus);
        AstralSounds.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            addItems(AstralItems.armor, event);
            addItems(AstralItems.tools, event);
            addItems(AstralItems.shields,event);
        } else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            addItems(AstralItems.basicItems, event);
            addItems(AstralItems.tools, event);
            addBlocks(AstralBlocks.allBlocks, event);
//            event.accept(AstralItems.SNOWFLAKE);
        }
    }

//    private void addItems(List<DeferredItem<Item>> items, Item after, BuildCreativeModeTabContentsEvent event) {
//        ItemStack prev = new ItemStack(after);
//        for (DeferredItem<Item> item : items) {
//            ItemStack i = item.toStack();
//            event.insertAfter(prev, i, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            prev = i;
//        }
//    }

    private void addBlocks(List<DeferredBlock<Block>> items, BuildCreativeModeTabContentsEvent event) {
        for (DeferredBlock<Block> item : items) {
            event.accept(item);
        }
    }

    private void addItems(List<DeferredItem<Item>> items, BuildCreativeModeTabContentsEvent event) {
        for (DeferredItem<Item> item : items) {
            event.accept(item);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    public static ResourceLocation resource(String loc) {
        return ResourceLocation.fromNamespaceAndPath(MODID, loc);
    }
}
