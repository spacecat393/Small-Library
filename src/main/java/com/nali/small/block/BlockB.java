package com.nali.small.block;

import com.nali.small.block.memo.IBothB;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockB extends Block implements IMixB
{
    public IBothB ibothb;

    public BlockB(Material material)
    {
        super(material);
        this.Binit();
    }
}
