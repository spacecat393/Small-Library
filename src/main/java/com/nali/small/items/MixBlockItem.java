package com.nali.small.items;

import com.nali.draw.DrawScreen;
import com.nali.render.ObjectRender;
import com.nali.small.blocks.IMixBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MixBlockItem extends ItemBlock implements IMixItems
{
    public MixBlockItem(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ObjectRender getObjectRender()
    {
        return ((IMixBlocks)this.block).getObjectRender();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public DrawScreen getDrawScreen()
    {
        return ((IMixBlocks)this.block).getDrawScreen();
    }
}
