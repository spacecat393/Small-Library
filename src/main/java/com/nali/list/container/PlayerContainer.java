package com.nali.list.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class PlayerContainer extends Container
{
    public static int ID;
    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
