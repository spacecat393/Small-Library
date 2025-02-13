package com.nali.small.mix.item;

import com.nali.small.Small;
import com.nali.small.mix.block.BlockRegistry;
import com.nali.small.mix.block.tile.TileRegistry;
import com.nali.system.Reflect;
import com.nali.system.StringReader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.nali.Nali.error;

@Mod.EventBusSubscriber(modid = Small.ID)
public class ItemRegistry
{
//	public static List<Class> ITEMS_CLASS_LIST = Reflect.getClasses("com.nali.list.items");
	public static Item[] ITEM_ARRAY;
	static
	{
		List<Class> items_class_list = Reflect.getClasses("com.nali.list.item");
//		ITEMS_CLASS_LIST.sort(Comparator.comparing(Class::getName));
//		items_class_list.sort(Comparator.comparing(Class::getName));
//		int list_size = ITEMS_CLASS_LIST.size();
		int size = items_class_list.size();
		ITEM_ARRAY = new Item[size];
//		int rg = 0;
//		for (Class clasz : ITEMS_CLASS_LIST)
		for (int i = 0; i < size; ++i)
		{
			try
			{
				Class clasz = items_class_list.get(i);
				Item item = (Item)clasz.getConstructor(String[].class).newInstance((Object)StringReader.get(clasz));
				clasz.getField("ID").set(null, i);
				ITEM_ARRAY[i] = item;
//				ITEM_ARRAY[rg++] = (Item)constructor.newInstance(clasz.getSimpleName().toLowerCase());
			}
			catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e)
			{
				error(e);
			}
		}
	}

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		for (Item item : ITEM_ARRAY)
		{
			event.getRegistry().register(item);
		}

		for (Item item : BlockRegistry.ITEM_ARRAY)
		{
			event.getRegistry().register(item);
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onModelRegistryEvent(ModelRegistryEvent event)
	{
		TileRegistry.onModelRegistryEvent(event);

		for (Item item : ITEM_ARRAY)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		for (Item item : BlockRegistry.ITEM_ARRAY)
		{
//			Item item = Item.getItemFromBlock(block);
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

//		for (Block block : BlocksRegistryHelper.BLOCK_ARRAY)
//		{
//			Item item = Item.getItemFromBlock(block);
////			if (item == Items.AIR)
////			{
////				Nali.error("AIR");
////			}
//			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
//		}
	}
}
