package org.lightning323.astral.item.glowHelmet;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.core.Holder;

public class GlowHelmetItem extends ArmorItem {

    public GlowHelmetItem(Holder<ArmorMaterial> material, Properties properties) {
        // In 1.21.1, ArmorItem takes (Material, Type, Properties)
        super(material, ArmorItem.Type.HELMET, properties);
    }

//    @Override
//    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
//        if (!level.isClientSide && entity instanceof Player player) {
//            // Check if the player is wearing this helmet in the helmet slot
//            if (player.getItemBySlot(EquipmentSlot.HEAD) == stack) {
////                applyNightVision(player);
//            }
//        }
//    }

//    private void applyNightVision(Player player) {
//        // 220 ticks (11 seconds) prevents the "flashing" effect when the timer runs low
//        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, true));
//    }
}