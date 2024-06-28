package com.nali.small;

import com.nali.Nali;
import com.nali.small.capability.CapabilityRegistry;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.gui.GuiHandler;
import com.nali.small.tile.TileRegistry;
import com.nali.small.chunk.ChunkCallBack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.HashMap;

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

//        if (event.getSide().isClient())
//        {
//            RenderHelper.init();
//        }

//        EntitiesRegistry.ENTITIES_CLASS_LIST = null;
        TileRegistry.TILES_CLASS_LIST = null;
    }

    @EventHandler
    public void onFMLServerStartedEvent(FMLServerStartedEvent event)
    {
        ServerE.ENTITIES_MAP = new HashMap();
        ChunkCallBack.set();
        MixAILe.init();
    }

    @EventHandler
    public void onFMLServerStoppingEvent(FMLServerStoppingEvent event)
    {
        ServerE.ENTITIES_MAP = null;
        MixAILe.AI_CLASS_LIST = null;
    }

//    @Mod.EventBusSubscriber(modid = Nali.ID)
//    public static class OK
//    {
//        @SubscribeEvent
//        public static void onLivingDeathEvent(LivingDeathEvent event)
//        {
//            I.logger.info("Some Death");
//            Entity e = event.getEntityLiving();
//            if (e instanceof EntitySheep && !e.world.isRemote)
//            {
//                I.logger.info("GET Sheep");
//                Entity e2 = event.getSource().getTrueSource();
//                if (e2 instanceof EntityPlayerMP)
//                {
//                    EntityPlayerMP p = (EntityPlayerMP)e2;
//                    Advancement advancement = p.getServer().getAdvancementManager().getAdvancement(new ResourceLocation(ID, "test/kill_a_mob"));
//                    AdvancementProgress progress = p.getAdvancements().getProgress(advancement);
//                    if (!progress.isDone())
//                    {
//                        I.logger.info("try unlock");
//                        for (String criterion : progress.getRemaningCriteria())
//                        {
//                            p.getAdvancements().grantCriterion(advancement, criterion);
//                        }
//                    }
//                }
//            }
//        }
//    }
}
