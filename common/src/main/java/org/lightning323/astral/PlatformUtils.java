package org.lightning323.astral;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.function.Supplier;

public class PlatformUtils {
    @ExpectPlatform
    public static Supplier<Attribute> getEntityReach() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Attribute> getBlockReach() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Attribute> getStepHeight() {
        throw new AssertionError();
    }

}