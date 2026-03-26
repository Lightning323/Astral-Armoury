package org.lightning323.astral.fabric;

//import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;

import net.minecraft.world.entity.ai.attributes.Attribute;
import org.lightning323.astral.Platform;

import java.util.function.Supplier;

public class PlatformFabric implements Platform {

    @Override
    public boolean isDataGen() {
        return false;
    }

    public Supplier<Attribute> getEntityReach() {
        return () -> null;
    }

    public Supplier<Attribute> getBlockReach() {
        return () -> null;
    }

    public Supplier<Attribute> getStepHeight() {
        return () -> null;
    }
}