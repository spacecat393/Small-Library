package com.nali.small.items;

import com.nali.draw.DrawScreen;
import com.nali.render.ObjectRender;
import com.nali.small.blocks.IMixBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class MixBlockItem extends ItemBlock implements IMixItems
{
    public MixBlockItem(Block block)
    {
        super(block);
    }

    @Override
    public ObjectRender getObjectRender()
    {
        return ((IMixBlocks)this.block).getObjectRender();
    }

    @Override
    public DrawScreen getDrawScreen()
    {
        return ((IMixBlocks)this.block).getDrawScreen();
    }
}
