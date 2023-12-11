package com.nali.ilol;

import com.nali.entities.EntitiesRender;
import com.nali.ilol.capabilities.CapabilitiesRegistryHelper;
import com.nali.ilol.entities.EntitiesRegistryHelper;
import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.gui.OpenGUIHelper;
import com.nali.ilol.networks.NetworksRegistry;
import com.nali.ilol.system.Reference;
import com.nali.system.Reflect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.WeakHashMap;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME)
public class ILOL
{
    @Instance
    public static ILOL I;

    public static Logger LOGGER;

    @EventHandler
    public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
    {
        LOGGER = event.getModLog();
//        MinecraftForge.EVENT_BUS.register(this);

        if (event.getSide().isClient())
        {
            EntitiesRender.set();
            CapabilitiesRegistryHelper.update();
//            if (Loader.isModLoaded(com.nali.system.Reference.MOD_ID))
//            {
//
//            }
            OpenGUIHelper.GUI_CLASS_LIST = Reflect.getClasses("com.nali.list.gui");
            OpenGUIHelper.GUI_CLASS_LIST.sort(Comparator.comparing(Class::getName));

            SkinningEntities.CLIENT_ENTITIES_MAP = new WeakHashMap<>();
        }
    }

    @EventHandler
    public void onFMLInitializationEvent(FMLInitializationEvent event)
    {
        EntitiesRegistryHelper.set();
        NetworksRegistry.register();
        CapabilitiesRegistryHelper.register();
    }

    @EventHandler
    public void onFMLServerStartedEvent(FMLServerStartedEvent event)
    {
        SkinningEntities.SERVER_ENTITIES_MAP = new WeakHashMap<>();
    }
}
