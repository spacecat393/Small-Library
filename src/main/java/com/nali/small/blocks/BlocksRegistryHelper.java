package com.nali.small.blocks;

import com.nali.small.Small;
import com.nali.system.Reflect;
import com.nali.system.StringReader;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BlocksRegistryHelper
{
    public static Block[] BLOCK_ARRAY;
    static
    {
        List<Class> blocks_class_list = Reflect.getClasses("com.nali.list.blocks");
        int size = blocks_class_list.size();
        BLOCK_ARRAY = new Block[size];

        for (int i = 0; i < size; ++i)
        {
            try
            {
                Class clasz = blocks_class_list.get(i);
                Block block = (Block)clasz.getConstructor(String[].class).newInstance((Object)StringReader.get(clasz));
                clasz.getField("ID").set(null, i);
                BLOCK_ARRAY[i] = block;
            }
            catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e)
            {
                Small.error(e);
            }
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        for (Block block : BLOCK_ARRAY)
        {
            event.getRegistry().register(block);
        }
    }
}
