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
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AstralSounds {
    public static final String MOD_ID = "astral"; // Replace with your actual MOD_ID variable

    // In 1.21, we use DeferredRegister
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);

    public static final Supplier<SoundEvent> SPIKES_ON = SOUND_EVENTS.register("spikes_on",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, "spikes_on")));

    public static final Supplier<SoundEvent> SPIKES_OFF = SOUND_EVENTS.register("spikes_off",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, "spikes_off")));

    public static final Supplier<SoundEvent> EQUIP_EMERALD = SOUND_EVENTS.register("equip_emerald",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, "equip_emerald")));

    // Make sure to call this in your main Mod constructor: AstralSounds.SOUND_EVENTS.register(bus);
    public static void register() {
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