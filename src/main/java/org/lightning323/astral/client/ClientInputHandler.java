//package org.lightning323.astral.client;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.player.LocalPlayer;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.ScaffoldingBlock;
//import net.neoforged.api.distmarker.Dist;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.client.event.InputEvent;
//import org.lwjgl.glfw.GLFW;
//
//@EventBusSubscriber(modid = "your_mod_id", value = Dist.CLIENT)
//public class ClientInputHandler {
//
//    @SubscribeEvent
//    public static void onMouseInput(InputEvent.MouseButton.Pre event) {
//        Minecraft mc = Minecraft.getInstance();
//        if (mc.level == null || mc.player == null || mc.screen != null) return;
//
//        // 1 = Right Click
//        if (event.getButton() == 1 && event.getAction() == GLFW.GLFW_PRESS) {
//            LocalPlayer player = mc.player;
//            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
//
//            // Your specific conditions
//            if (player.isShiftKeyDown() || stack.isEmpty()) {
//                return;
//            }
//
//            Block heldBlock = Block.byItem(stack.getItem());
//
//            // Ensure we aren't creating a loop
//            if (heldBlock != Blocks.AIR && !(heldBlock instanceof ScaffoldingBlock)) {
//                // Because we are on the Client, we cannot call level.destroyBlock directly.
//                // You MUST send a packet to the server here.
//                // PacketDistributor.SERVER.noArg().send(new MyScaffoldingSwapPacket());
//            }
//        }
//    }
//}