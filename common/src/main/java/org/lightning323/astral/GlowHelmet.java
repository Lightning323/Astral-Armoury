package org.lightning323.astral;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.ArmorItem;
import org.lightning323.astral.client.ClientGameSettings;
import org.lightning323.astral.registries.AstralArmorMaterials;

public class GlowHelmet extends ArmorItem {
    private static double DEFAULT_GAMMA = 1.0D;
    public static final double MAX_GAMMA = 8.5;

    public static void setNightVision(boolean enabled) {
        if(enabled){
            GlowHelmet.DEFAULT_GAMMA = ClientGameSettings.getGammaClamped();
            ClientGameSettings.setGamma(GlowHelmet.MAX_GAMMA);
        }
      else  ClientGameSettings.setGamma(GlowHelmet.DEFAULT_GAMMA);
    }

    public GlowHelmet(Properties properties) {
        super(
                AstralArmorMaterials.GLOWING_ARMOR,//Armor material
                Type.HELMET, properties); //Properties
    }

}