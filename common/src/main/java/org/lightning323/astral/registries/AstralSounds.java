package org.lightning323.astral.registries;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
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
import net.minecraft.world.level.Level;
import org.lightning323.astral.AstralArmoury;

import java.util.Iterator;

import static org.lightning323.astral.AstralArmoury.MOD_ID;

public class AstralSounds {
  public static final Registrar<SoundEvent> SOUND_EVENTS = AstralArmoury.REGISTRIES.get().get(Registries.SOUND_EVENT);

  public static final RegistrySupplier<SoundEvent> SPIKES_ON = SOUND_EVENTS.<SoundEvent>register(AstralArmoury.resource("spikes_on"), () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "spikes_on")));
  public static final RegistrySupplier<SoundEvent> SPIKES_OFF = SOUND_EVENTS.<SoundEvent>register(AstralArmoury.resource("spikes_off"), () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "spikes_off")));
  public static final RegistrySupplier<SoundEvent> EQUIP_EMERALD = SOUND_EVENTS.<SoundEvent>register(AstralArmoury.resource("equip_emerald"), () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "equip_emerald")));

  public static void register() {
  }

  public static void playSoundAtBlock(Level world, Entity player, BlockPos pos, SoundEvent soundIn) {
      world.playSound(player, pos, soundIn, SoundSource.BLOCKS, 1.0F, 1.0F);
  }

  public static void playSound(Level world, BlockPos pos, SoundEvent soundIn) {
      world.playLocalSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), soundIn, SoundSource.BLOCKS, 0.5F, 0.5F, false);
  }

  public static void playSound(Level world, BlockPos pos, SoundEvent soundIn, float volume) {
      world.playLocalSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), soundIn, SoundSource.BLOCKS, volume, 0.5F, false);
  }

  public static void playSound(Entity entityIn, SoundEvent soundIn) {
      playSound(entityIn, soundIn, 1.0F, 1.0F);
  }

  public static void playSound(Entity entityIn, SoundEvent soundIn, float volume) {
      playSound(entityIn, soundIn, volume, 1.0F);
  }

  public static void playSound(Entity entityIn, SoundEvent soundIn, float volume, float pitch) {
      if (entityIn != null && entityIn.level().isClientSide) {
          entityIn.playSound(soundIn, volume, pitch);
      }
  }

  public static void playSoundFromServer(ServerPlayer entityIn, BlockPos pos, SoundEvent soundIn, float vol, float pitch) {
      if (soundIn != null && entityIn != null) {
          entityIn.connection.send(new ClientboundSoundPacket(Holder.direct(soundIn), SoundSource.BLOCKS, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), vol, pitch, entityIn.level().getRandom().nextLong()));
      }
  }

  public static void playSoundFromServer(ServerPlayer entityIn, SoundEvent soundIn, float vol, float pitch) {
      if (soundIn != null && entityIn != null) {
          entityIn.connection.send(new ClientboundSoundPacket(Holder.direct(soundIn), SoundSource.BLOCKS, entityIn.xOld, entityIn.yOld, entityIn.zOld, vol, pitch, entityIn.level().getRandom().nextLong()));
      }
  }

  public static void playSoundFromServer(ServerLevel world, BlockPos pos, SoundEvent soundIn) {
      Iterator var3 = world.players().iterator();

      while(var3.hasNext()) {
          ServerPlayer sp = (ServerPlayer)var3.next();
          playSoundFromServer(sp, pos, soundIn, 1.0F, 1.0F);
      }

  }
}
