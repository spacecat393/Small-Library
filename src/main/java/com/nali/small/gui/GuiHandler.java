package com.nali.small.gui;

import com.nali.system.Reflect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

import static com.nali.Nali.error;

public class GuiHandler implements IGuiHandler
{
    @SideOnly(Side.CLIENT)
    public static List<Class> GUI_CLASS_LIST;
    public static List<Class> CONTAINER_CLASS_LIST;

    static
    {
        CONTAINER_CLASS_LIST = Reflect.getClasses("com.nali.list.container");
        CONTAINER_CLASS_LIST.sort(Comparator.comparing(Class::getName));
        for (int i = 0; i < CONTAINER_CLASS_LIST.size(); ++i)
        {
            try
            {
                CONTAINER_CLASS_LIST.get(i).getField("ID").set(null, i);
            }
            catch (IllegalAccessException | NoSuchFieldException e)
            {
                error(e);
            }
        }

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            clientInit();
        }
    }

    @SideOnly(Side.CLIENT)
    public static void clientInit()
    {
        GUI_CLASS_LIST = Reflect.getClasses("com.nali.list.container.gui");
        GUI_CLASS_LIST.sort(Comparator.comparing(Class::getName));
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        try
        {
            return CONTAINER_CLASS_LIST.get(id).getMethod("get", EntityPlayer.class, World.class, int.class, int.class, int.class).invoke(null, entityplayer, world, x, y, z);
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            error(e);
        }

        return null;
    }

    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int id, EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        try
        {
            return GUI_CLASS_LIST.get(id).getMethod("get", EntityPlayer.class, World.class, int.class, int.class, int.class).invoke(null, entityplayer, world, x, y, z);
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            error(e);
        }

        return null;
    }
}
