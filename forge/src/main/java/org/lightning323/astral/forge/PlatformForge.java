package org.lightning323.astral.forge;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.common.ForgeMod;
import org.lightning323.astral.Platform;

import java.util.function.Supplier;

import static org.lightning323.astral.AstralArmoury.LOGGER;

public class PlatformForge implements Platform {

    public PlatformForge() {
        isDataGen = System.getProperty("sun.java.command", "").contains("datagen");
        LOGGER.debug("Forge data-gen = {}", isDataGen);
    }

    private final boolean isDataGen;

    public boolean isDataGen() {
        return isDataGen;
    }

    public Supplier<Attribute> getEntityReach() {
        return ForgeMod.ENTITY_REACH;
    }

    public Supplier<Attribute> getBlockReach() {
        return ForgeMod.BLOCK_REACH;
    }

    public Supplier<Attribute> getStepHeight() {
        return net.minecraftforge.common.ForgeMod.STEP_HEIGHT_ADDITION;
    }
}