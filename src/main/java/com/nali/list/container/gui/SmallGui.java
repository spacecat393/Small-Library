package com.nali.list.container.gui;

import com.nali.list.container.SmallContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SmallGui extends GuiContainer
{
    public SmallGui(Container inventorySlotsIn)
    {
        super(inventorySlotsIn);
    }

    public static SmallGui get(EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        return new SmallGui(new SmallContainer());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
    }
}
