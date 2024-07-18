package com.nali.small;

import com.nali.small.capability.CapabilityRegistry;
import com.nali.small.chunk.ChunkCallBack;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.gui.GuiHandler;
import com.nali.small.tile.TileRegistry;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.HashMap;

import static com.nali.small.gui.page.Page.STRING_ARRAY;

@Mod(modid = Small.ID)
public class Small
{
    public final static String ID = "small";

    @Instance
    public static Small I;

//    @EventHandler
//    public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
//    {
////        if (event.getSide().isClient())
////        {
//////            DataLoader.setModels(RenderHelper.DATALOADER, Small.ID);
//////            CapabilitiesRegistryHelper.update();
////            OpenGUIHelper.set();
////        }
//    }

    @EventHandler
    public void onFMLInitializationEvent(FMLInitializationEvent event)
    {
        EntityRegistry.set();
        CapabilityRegistry.register();
        NetworkRegistry.INSTANCE.registerGuiHandler(I, new GuiHandler());
    }

    @EventHandler
    public void onFMLPostInitializationEvent(FMLPostInitializationEvent event)
    {
//        EntityRegistry.ENTITY_KEY_ARRAY = new HashSet(ENTITY_CLASS_ENTRIES.keySet()).toArray();

        if (event.getSide().isClient())
        {
            byte size = 19;
            String t_string = "info." + Small.ID + ".t";
            STRING_ARRAY = new String[size];
//            FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
            for (byte i = 0; i < size; ++i)
            {
//                String string = I18n.translateToLocal(t_string + i);
//                STRING_ARRAY[i] = string;
//                byte tw = (byte)fontrenderer.getStringWidth(string);
//                if (MAX_TW < tw)
//                {
//                    MAX_TW = tw;
//                }
                STRING_ARRAY[i] = I18n.translateToLocal(t_string + i);
            }
//            RenderHelper.init();
        }

//        EntitiesRegistry.ENTITIES_CLASS_LIST = null;
        TileRegistry.TILES_CLASS_LIST = null;
    }

    @EventHandler
    public void onFMLServerStartedEvent(FMLServerStartedEvent event)
    {
        ServerE.S_MAP = new HashMap();
        ChunkCallBack.set();
//        MixAIE.init();
    }

    @EventHandler
    public void onFMLServerStoppingEvent(FMLServerStoppingEvent event)
    {
        ServerE.S_MAP = null;
//        MixAIE.AI_CLASS_LIST = null;
    }
}
