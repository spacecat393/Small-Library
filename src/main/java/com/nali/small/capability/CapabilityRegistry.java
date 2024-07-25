package com.nali.small.capability;

import com.nali.small.Small;
import com.nali.system.Reflect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Comparator;
import java.util.List;

import static com.nali.Nali.error;

@Mod.EventBusSubscriber(modid = Small.ID)
public class CapabilityRegistry
{
    public static List<Class> CAPABILITIES_SERIALIZATIONS_CLASS_LIST = Reflect.getClasses("com.nali.list.capability.serializable");
    public static ResourceLocation[] RESOURCELOCATION_ARRAY;
    static
    {
        CAPABILITIES_SERIALIZATIONS_CLASS_LIST.sort(Comparator.comparing(Class::getName));
    }
//    public static List<Object> CLIENT_CAPABILITY_OBJECT_LIST;

//    public static void update()
//    {
//        CLIENT_CAPABILITY_OBJECT_LIST = new ArrayList();
//
//        while (CLIENT_CAPABILITY_OBJECT_LIST.size() != RESOURCELOCATION_ARRAY.length)
//        {
//            CLIENT_CAPABILITY_OBJECT_LIST.add(new Object());
//        }
//    }

    public static void register()
    {
        List<Class> capabilities_storages_class_list = Reflect.getClasses("com.nali.list.capability.storage");
        List<Class> capabilities_factories_class_list = Reflect.getClasses("com.nali.list.capability.factory");
        List<Class> capabilities_types_class_list = Reflect.getClasses("com.nali.list.capability.type");
        capabilities_storages_class_list.sort(Comparator.comparing(Class::getName));
        capabilities_factories_class_list.sort(Comparator.comparing(Class::getName));
        capabilities_types_class_list.sort(Comparator.comparing(Class::getName));

        RESOURCELOCATION_ARRAY = new ResourceLocation[capabilities_storages_class_list.size()];
        int index = 0;
        for (Class clasz : capabilities_storages_class_list)
        {
            RESOURCELOCATION_ARRAY[index++] = new ResourceLocation(Small.ID, clasz.getSimpleName().toLowerCase());
        }

        for (int i = 0; i < RESOURCELOCATION_ARRAY.length; ++i)
        {
            try
            {
                CapabilityManager.INSTANCE.register(capabilities_types_class_list.get(i), (Capability.IStorage)capabilities_storages_class_list.get(i).newInstance(), capabilities_factories_class_list.get(i));
            }
            catch (InstantiationException | IllegalAccessException e)
            {
                error(e);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityAttachCapabilitiesEvent(net.minecraftforge.event.AttachCapabilitiesEvent<Entity> event)
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
                error(e);
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
