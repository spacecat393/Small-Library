package com.nali.small.block;

import com.nali.Nali;
import com.nali.small.Small;
import com.nali.small.tile.TileRegistry;
import com.nali.system.Reflect;
import com.nali.system.StringReader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Mod.EventBusSubscriber(modid = Small.ID)
public class BlockRegistry
{
    public static Item[] ITEM_ARRAY;
    public static Block[] BLOCK_ARRAY;
    static
    {
        List<Class> blocks_class_list = Reflect.getClasses("com.nali.list.block");
        int size = blocks_class_list.size();
        BLOCK_ARRAY = new Block[size];
        ITEM_ARRAY = new Item[size];

        for (int i = 0; i < size; ++i)
        {
            try
            {
                Class clasz = blocks_class_list.get(i);
                Block block = (Block)clasz.getConstructor(String[].class).newInstance((Object)StringReader.get(clasz));
                clasz.getField("ID").set(null, i);
                BLOCK_ARRAY[i] = block;
                ITEM_ARRAY[i] = ((Item)clasz.getMethod("getNewItem").invoke(block)).setRegistryName(block.getRegistryName());
            }
            catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e)
            {
                Nali.I.error(e);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
//        for (int i = 0; i < BLOCK_ARRAY.length; ++i)
        for (Block block : BLOCK_ARRAY)
        {
//            Block block = BLOCK_ARRAY[i];
            event.getRegistry().register(block);
//            ITEM_ARRAY[i] = ((MixBlocks)block).getNewItem(block).setRegistryName(block.getRegistryName());
        }
        TileRegistry.onBlockRegister(event);
    }
}
