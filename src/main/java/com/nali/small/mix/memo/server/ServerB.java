package com.nali.small.mix.memo.server;

import com.nali.small.mix.memo.IBothB;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ServerB<T extends TileEntity, E extends Block> extends ServerN implements IBothB<T, E>
{
    @SideOnly(Side.CLIENT)
    @Override
    public void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
    }
}
