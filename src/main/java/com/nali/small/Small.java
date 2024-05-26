package com.nali.small;

import com.nali.small.capabilities.CapabilitiesRegistry;
import com.nali.small.entities.EntitiesRegistry;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.gui.GuiHandler;
import com.nali.small.render.RenderHelper;
import com.nali.small.system.Reference;
import com.nali.small.tiles.TileRegistry;
import com.nali.small.world.ChunkCallBack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;

import static com.nali.small.entities.EntitiesRegistry.ENTITY_CLASS_ENTRIES;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME)
public class Small
{
    @Instance
    public static Small I;

    public static Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

//    @EventHandler
//    public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
//    {
////        if (event.getSide().isClient())
////        {
//////            DataLoader.setModels(RenderHelper.DATALOADER, Reference.MOD_ID);
//////            CapabilitiesRegistryHelper.update();
////            OpenGUIHelper.set();
////        }
//    }

    @EventHandler
    public void onFMLInitializationEvent(FMLInitializationEvent event)
    {
        EntitiesRegistry.set();
        CapabilitiesRegistry.register();
        NetworkRegistry.INSTANCE.registerGuiHandler(I, new GuiHandler());
    }

    @EventHandler
    public void onFMLPostInitializationEvent(FMLPostInitializationEvent event)
    {
        EntitiesRegistry.ENTITY_KEY_ARRAY = new HashSet(ENTITY_CLASS_ENTRIES.keySet()).toArray();

        if (event.getSide().isClient())
        {
            RenderHelper.init();
        }

        EntitiesRegistry.ENTITIES_CLASS_LIST = null;
        TileRegistry.TILES_CLASS_LIST = null;
    }

    @EventHandler
    public void onFMLServerStartedEvent(FMLServerStartedEvent event)
    {
        ServerEntitiesMemory.ENTITIES_MAP = new HashMap();
        ChunkCallBack.set();
    }

    public static void error(Throwable t)
    {
        LOGGER.error(t, t);
        FMLCommonHandler.instance().exitJava(-1, true);
    }

    public static void error(String s)
    {
        LOGGER.error(s);
        FMLCommonHandler.instance().exitJava(-1, true);
    }

    public static void warn(Throwable t)
    {
        LOGGER.warn(t, t);
    }
}
