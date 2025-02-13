package com.nali.small.world;

import com.nali.Nali;
import com.nali.small.Small;
import com.nali.system.Reflect;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

@Mod.EventBusSubscriber(modid = Small.ID)
public class WorldRegistry
{
	public static void set()
	{
		List<Class> gen_class_list = Reflect.getClasses("com.nali.list.world.gen");
		for (Class gen_class : gen_class_list)
		{
			try
			{
				GameRegistry.registerWorldGenerator((IWorldGenerator)gen_class.newInstance(), 0);
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				Nali.error(e);
			}
		}
	}

//	@SubscribeEvent
//	public static void onInitMapGenEvent(InitMapGenEvent event)
//	{
//		Nali.error("onInitMapGenEvent");
//		if (event.getType() == InitMapGenEvent.EventType.CUSTOM)
//		{
//			List<Class> structure_class_list = Reflect.getClasses("com.nali.list.world.structure");
//			for (Class structure_class : structure_class_list)
//			{
//				Nali.warn("structure_class " + structure_class);
//				try
//				{
//					event.setNewGen((MapGenStructure)structure_class.newInstance());
//				}
//				catch (InstantiationException | IllegalAccessException e)
//				{
//					Nali.error(e);
//				}
//			}
//		}
//	}

//	@SubscribeEvent
//	public static void onPopulateChunkEvent(PopulateChunkEvent event)
//	{
//		int chunkX = event.getChunkX();
//		int chunkZ = event.getChunkZ();
//		if (chunkX % 8 == 0 && chunkZ % 8 == 0)
//		{
//		}
//	}
}
