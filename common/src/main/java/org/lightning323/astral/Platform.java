package org.lightning323.astral;

import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.function.Supplier;

public interface Platform {

    public abstract boolean isDataGen();

    public abstract Supplier<Attribute> getEntityReach();

    public abstract Supplier<Attribute> getBlockReach();

    public abstract Supplier<Attribute> getStepHeight();
}