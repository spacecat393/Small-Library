package com.nali.small.block;

import com.nali.small.block.memo.IBothB;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IMixB<B extends IBothB>
{
    default void Binit()
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            this.newC();
        }
        else
        {
            this.newS();
        }
    }

//    B getB();

    @SideOnly(Side.CLIENT)
    void newC();

    void newS();
}
