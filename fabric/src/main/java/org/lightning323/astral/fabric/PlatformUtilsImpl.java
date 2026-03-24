package org.lightning323.astral.fabric;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.tterrag.registrate.Registrate;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.lightning323.astral.AstralArmoury;

import java.util.function.Supplier;

public class PlatformUtilsImpl {

    public static Supplier<Attribute> getEntityReach() {
        return () -> ReachEntityAttributes.ATTACK_RANGE;
    }

    public static Supplier<Attribute> getBlockReach() {
        return () -> ReachEntityAttributes.REACH;
    }

    public static Supplier<Attribute> getStepHeight() {
        return () -> null;
    }
}