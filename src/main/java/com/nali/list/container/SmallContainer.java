package com.nali.list.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

import java.util.List;

public class SmallContainer extends Container
{
    public static int ID;

    public static List<Integer> INTEGER_LIST;

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

    public static SmallContainer get(EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        return new SmallContainer();
    }
}
