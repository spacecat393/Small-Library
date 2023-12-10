package com.nali.ilol.capabilities;

import com.nali.ilol.ILOL;
import com.nali.ilol.system.Reference;
import com.nali.system.Reflect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CapabilitiesRegistryHelper
{
    public static List<Class> CAPABILITIES_STORAGES_CLASS_LIST = Reflect.getClasses("com.nali.list.capabilitiesstorages");
    public static List<Class> CAPABILITIES_FACTORIES_CLASS_LIST = Reflect.getClasses("com.nali.list.capabilitiesfactories");
    public static List<Class> CAPABILITIES_SERIALIZATIONS_CLASS_LIST = Reflect.getClasses("com.nali.list.capabilitiesserializations");
    public static List<Class> CAPABILITIES_TYPES_CLASS_LIST = Reflect.getClasses("com.nali.list.capabilitiestypes");
    public static ResourceLocation[] RESOURCELOCATION_ARRAY;
    static
    {
        CAPABILITIES_STORAGES_CLASS_LIST.sort(Comparator.comparing(Class::getName));
        CAPABILITIES_FACTORIES_CLASS_LIST.sort(Comparator.comparing(Class::getName));
        CAPABILITIES_SERIALIZATIONS_CLASS_LIST.sort(Comparator.comparing(Class::getName));
        CAPABILITIES_TYPES_CLASS_LIST.sort(Comparator.comparing(Class::getName));

        int index = 0;
        for (Class clasz : CAPABILITIES_STORAGES_CLASS_LIST)
        {
            RESOURCELOCATION_ARRAY[index++] = new ResourceLocation(Reference.MOD_ID, clasz.getSimpleName().toLowerCase());
        }
    }
    public static ArrayList<Object> CLIENT_CAPABILITY_OBJECT_ARRAYLIST;

    public static void update()
    {
        CLIENT_CAPABILITY_OBJECT_ARRAYLIST = new ArrayList();

        while (CLIENT_CAPABILITY_OBJECT_ARRAYLIST.size() != RESOURCELOCATION_ARRAY.length)
        {
            CLIENT_CAPABILITY_OBJECT_ARRAYLIST.add(new Object());
        }
    }

    public static void register()
    {
        for (int i = 0; i < RESOURCELOCATION_ARRAY.length; ++i)
        {
            try
            {
                CapabilityManager.INSTANCE.register(CAPABILITIES_TYPES_CLASS_LIST.get(i), (Capability.IStorage)CAPABILITIES_STORAGES_CLASS_LIST.get(i).newInstance(), CAPABILITIES_FACTORIES_CLASS_LIST.get(i));
            }
            catch (InstantiationException | IllegalAccessException e)
            {
                ILOL.LOGGER.error(e.getMessage(), e);
            }
        }
    }

    @SubscribeEvent
    public static void setEntityAttachCapabilitiesEvent(net.minecraftforge.event.AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;

        for (int i = 0; i < RESOURCELOCATION_ARRAY.length; ++i)
        {
            try
            {
                event.addCapability(RESOURCELOCATION_ARRAY[i], (ICapabilityProvider)CAPABILITIES_SERIALIZATIONS_CLASS_LIST.get(i).newInstance());
            }
            catch (InstantiationException | IllegalAccessException e)
            {
                ILOL.LOGGER.error(e.getMessage(), e);
            }
        }
    }

//    @SubscribeEvent
//    public static void onPlayerLogsIn(PlayerEvent.PlayerLoggedInEvent event)
//    {
//        EntityPlayer player = event.player;
//        PyroxeneTypes pyroxenetypes = player.getCapability(PyroxeneSerializations.PYROXENETYPES_CAPABILITY, null);
//
//        CutePomi.LOGGER.info("Pyroxene : " + pyroxenetypes.get());
//    }
//
//    @SubscribeEvent
//    public static void onPlayerSleep(PlayerSleepInBedEvent event)
//    {
//        EntityPlayer player = event.getEntityPlayer();
//
//        if (player.world.isRemote) return;
//
//        PyroxeneTypes pyroxenetypes = player.getCapability(PyroxeneSerializations.PYROXENETYPES_CAPABILITY, null);
//
//        pyroxenetypes.set(pyroxenetypes.get() + 1);
//
//        CutePomi.LOGGER.info("Pyroxene : " + pyroxenetypes.get());
//    }
}
