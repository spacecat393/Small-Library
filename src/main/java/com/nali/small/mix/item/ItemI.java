package com.nali.small.mix.item;

import com.nali.small.SmallTab;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothI;
import com.nali.small.mix.memo.IBothN;
import com.nali.small.mix.memo.server.ServerI;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemI extends Item implements IMixN
{
    public IBothI ibothi;

    public ItemI(String[] string_array)
    {
        this.Ninit();
        this.ibothi.init(this, string_array[0], string_array[1], SmallTab.TAB);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void render()
    {
        this.ibothi.render();
    }

    @Override
    public void updateLight(World world, BlockPos blockpos)
    {
        this.ibothi.updateLight(world, blockpos);
    }

    @Override
    public void light()
    {
        this.ibothi.light();
    }

    @Override
    public void newS()
    {
        this.ibothi = new ServerI();
    }

    @Override
    public IBothN getB()
    {
        return this.ibothi;
    }

    @Override
    public Item getE()
    {
        return this;
    }
}
