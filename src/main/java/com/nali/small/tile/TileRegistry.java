package com.nali.small.tile;

import com.nali.small.Small;
import com.nali.system.Reflect;
import com.nali.system.StringReader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

//@Mod.EventBusSubscriber(modid = Small.ID)
public class TileRegistry
{
    public static List<Class> TILES_CLASS_LIST = Reflect.getClasses("com.nali.list.block.tile");
    static
    {
        TILES_CLASS_LIST.sort(Comparator.comparing(Class::getName));
    }

//    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        for (int i = 0; i < TILES_CLASS_LIST.size(); ++i)
        {
//            try
//            {
            Class clasz = TILES_CLASS_LIST.get(i);
            String[] string_array = StringReader.get(clasz);
            GameRegistry.registerTileEntity(clasz, new ResourceLocation(string_array[1], string_array[0]));
//                clasz.getField("ID").set(null, i);
//            }
//            catch (IllegalAccessException | NoSuchFieldException e)
//            {
//                Small.error(e);
//            }
        }
    }

//    @SubscribeEvent
//    @SideOnly(Side.CLIENT)
    public static void onModelRegistryEvent(ModelRegistryEvent event)
    {
        List<Class> tilerender_class_list = Reflect.getClasses("com.nali.list.block.tile.render");
        tilerender_class_list.sort(Comparator.comparing(Class::getName));

        for (int i = 0; i < TILES_CLASS_LIST.size(); ++i)
        {
            Class tiles_clasz = TILES_CLASS_LIST.get(i);
            try
            {
                ClientRegistry.bindTileEntitySpecialRenderer(tiles_clasz, (TileEntitySpecialRenderer)tilerender_class_list.get(i).getConstructor().newInstance());
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
            {
                Small.error(e);
            }
        }
    }
}
