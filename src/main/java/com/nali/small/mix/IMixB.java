package com.nali.small.mix;

import com.nali.small.mix.memo.IBothB;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IMixB<B extends IBothB<T, E>, T extends TileEntity, E extends Block> extends IMixN<B, E>
{
    @SideOnly(Side.CLIENT)
    void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha);
}
