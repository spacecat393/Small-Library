package com.nali.small.item;

import com.nali.draw.DrawScreen;
import com.nali.render.ObjectRender;
import com.nali.small.block.BlockB;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MixBlockItem extends ItemBlock implements IMixItem
{
    public MixBlockItem(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ObjectRender getObjectRender()
    {
        return ((BlockB)this.block).getObjectRender();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public DrawScreen getDrawScreen()
    {
        return ((BlockB)this.block).getDrawScreen();
    }
}
