package com.nali.ilol.items;

import com.nali.ilol.ILOL;
import com.nali.ilol.system.Reference;
import com.nali.system.Reflect;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ItemsRegistryHelper
{
    public static List<Class> ITEMS_CLASS_LIST = Reflect.getClasses("com.nali.list.items");
    public static Item[] ITEM_ARRAY;
    static
    {
        ITEMS_CLASS_LIST.sort(Comparator.comparing(Class::getName));
        ITEM_ARRAY = new Item[ITEMS_CLASS_LIST.size()];
        int index = 0;
        for (Class clasz : ITEMS_CLASS_LIST)
        {
            try
            {
                Constructor constructor = clasz.getConstructor(String.class);
                ITEM_ARRAY[index++] = (Item)constructor.newInstance(clasz.getSimpleName().toLowerCase());
            }
            catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
            {
                ILOL.LOGGER.error(e.getMessage(), e);
            }
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        for (Item item : ITEM_ARRAY)
        {
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event)
    {
        for (Item item : ITEM_ARRAY)
        {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
