package org.lightning323.astral.registries;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.lightning323.astral.Astral;

import java.util.function.Supplier;

import static org.lightning323.astral.Astral.MODID;

public class AstralSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, MODID);

    public static final Holder<SoundEvent> SPIKES_ON = SOUND_EVENTS.register("spikes_on",
            () -> SoundEvent.createVariableRangeEvent(Astral.resource( "spikes_on")));

    public static final Holder<SoundEvent> SPIKES_OFF = SOUND_EVENTS.register("spikes_off",
            () -> SoundEvent.createVariableRangeEvent(Astral.resource( "spikes_off")));

    // Make sure to call this in your main Mod constructor: AstralSounds.SOUND_EVENTS.register(bus);
    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }

    public static void playSoundAtBlock(Level world, Entity player, BlockPos pos, SoundEvent soundIn) {
        world.playSound(player, pos, soundIn, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public static void playSound(Level world, BlockPos pos, SoundEvent soundIn) {
        world.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), soundIn, SoundSource.BLOCKS, 0.5F, 0.5F, false);
    }

    public static void playSound(Level world, BlockPos pos, SoundEvent soundIn, float volume) {
        world.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), soundIn, SoundSource.BLOCKS, volume, 0.5F, false);
    }

    public static void playSound(Entity entityIn, SoundEvent soundIn, float volume, float pitch) {
        if (entityIn != null && entityIn.level().isClientSide) {
            entityIn.playSound(soundIn, volume, pitch);
        }
    }

    public static void playSoundFromServer(ServerPlayer entityIn, BlockPos pos, SoundEvent soundIn, float vol, float pitch) {
        if (soundIn != null && entityIn != null) {
            entityIn.connection.send(new ClientboundSoundPacket(
                    Holder.direct(soundIn),
                    SoundSource.BLOCKS,
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    vol,
                    pitch,
                    entityIn.level().getRandom().nextLong()
            ));
        }
    }

    public static void playSoundFromServer(ServerLevel world, BlockPos pos, SoundEvent soundIn) {
        for (ServerPlayer sp : world.players()) {
            playSoundFromServer(sp, pos, soundIn, 1.0F, 1.0F);
        }
    }
}