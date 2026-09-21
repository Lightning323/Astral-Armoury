package org.lightning323.astral.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.lightning323.astral.entity.neko.SlingshotProjectile;
import org.lightning323.astral.entity.neko.WildfireTridentEntity;

import static org.lightning323.astral.Astral.MODID;

/**
 * Entity types for Neko-ported weapons/tools.
 */
public class AstralEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<SlingshotProjectile>> SLINGSHOT_PROJECTILE =
            ENTITY_TYPES.register("slingshot_projectile",
                    () -> EntityType.Builder.<SlingshotProjectile>of(SlingshotProjectile::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("slingshot_projectile"));

    public static final DeferredHolder<EntityType<?>, EntityType<WildfireTridentEntity>> WILDFIRE_TRIDENT =
            ENTITY_TYPES.register("wildfire_trident",
                    () -> EntityType.Builder.<WildfireTridentEntity>of(WildfireTridentEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20)
                            .build("wildfire_trident"));

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }
}
