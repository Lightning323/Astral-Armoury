package org.lightning323.astral.forge.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lightning323.astral.AstralArmoury;
import org.lightning323.astral.registries.ModItems;
import org.lightning323.astral.shield.AstralShieldRenderer;

@Mod.EventBusSubscriber(modid = AstralArmoury.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents {




    @SubscribeEvent
    public static void onRegisterReloadListener(RegisterClientReloadListenersEvent event) {
//        AstralArmoury.shieldRenderer = new AstralShieldRenderer();
//        event.registerReloadListener(AstralArmoury.shieldRenderer);
    }

}