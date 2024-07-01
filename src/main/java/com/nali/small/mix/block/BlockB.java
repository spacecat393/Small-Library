package com.nali.small.mix.block;

import com.nali.small.SmallTab;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothB;
import com.nali.small.mix.memo.IBothN;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockB extends Block implements IMixN
{
    public IBothB ibothb;

    public BlockB(String[] string_array, Material material)
    {
        super(material);
        this.ibothb.init(this, string_array[0], string_array[1], SmallTab.TAB);
        this.Ninit();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void render()
    {
        this.ibothb.render();
    }

    @Override
    public void updateLight(World world, BlockPos blockpos)
    {
        this.ibothb.updateLight(world, blockpos);
    }

    @Override
    public void light()
    {
        this.ibothb.light();
    }

    @Override
    public IBothN getB()
    {
        return this.ibothb;
    }
}
