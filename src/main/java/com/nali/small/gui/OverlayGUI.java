//package com.nali.cutepomi.gui;
//
//import com.nali.cutepomi.capabilities.serializations.PyroxeneSerializations;
//import com.nali.cutepomi.capabilities.types.PyroxeneTypes;
//import com.nali.cutepomi.system.Reference;
//import net.minecraft.client.Minecraft;
//import net.minecraftforge.client.event.RenderGameOverlayEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//
//@Mod.EventBusSubscriber(modid = Small.ID)
//public class OverlayGUI
//{
//    @SubscribeEvent
//    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event)
//    {
//        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL)
//        {
//            PyroxeneTypes pyroxenetypes = Minecraft.getMinecraft().player.getCapability(PyroxeneSerializations.PYROXENETYPES_CAPABILITY, null);
//            Minecraft.getMinecraft().fontRenderer.drawString("" + pyroxenetypes.get(), 10, 10, 0xFFFFFF);
//        }
//    }
//}
