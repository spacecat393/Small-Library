package com.nali.list.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class PlayerContainer extends Container
{
    public static int ID;

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }

    public static PlayerContainer get(EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        return new PlayerContainer();
    }
}
