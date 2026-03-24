package org.lightning323.astral.forge;

import com.tterrag.registrate.Registrate;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.common.ForgeMod;
import org.lightning323.astral.AstralArmoury;

import java.util.function.Supplier;

public class PlatformUtilsImpl {

    public static Supplier<Attribute> getEntityReach() {
        return ForgeMod.ENTITY_REACH;
    }

    public static Supplier<Attribute> getBlockReach() {
        return ForgeMod.BLOCK_REACH;
    }

    public static Supplier<Attribute> getStepHeight() {
        return net.minecraftforge.common.ForgeMod.STEP_HEIGHT_ADDITION;
    }
}